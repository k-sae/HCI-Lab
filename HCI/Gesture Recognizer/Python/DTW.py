from math import sin, cos, atan2, sqrt
import json
NumPoints = 64
SquareSize = 250.0
AngleRange = 45.0
AnglePrecision = 2.0
Phi = 0.5 * (-1.0 + sqrt(5.0))


class Template:
    def __init__(self, name, points):
        self.points = points
        self.name = name
        self.points = resample(self.points, NumPoints)
        self.points = rotate_to_zero(self.points)
        self.points = scale_to_square(self.points, SquareSize)
        self.points = translate_to_origin(self.points)


class Result:
    def __init__(self, name, score):
        self.Name = name
        self.Score = score


class DTWRecognizer:
    def __init__(self):
        self.templates = []
        
        with open('Templates.json') as data_file:    
             data = json.load(data_file)
        for i in range(len(data['Templates'])) : 
            templete = data['Templates'][i]['Name']
            points = []
            for m in range(len(data['Templates'][i]['Points'])) : 
                point = [data['Templates'][i]['Points'][m]['x'],data['Templates'][i]['Points'][m]['y']]
                points.append(point)
                
            
            self.templates.append(Template(templete,points))
            

        #   
        '''
        self.templates.append(Template("Zig-Zag",[[387, 192],[388, 192],[388, 190],[388, 189],[388, 188],[388, 187],[388, 186],[388, 185],[388, 184],[389, 182],[389, 180],[390, 180],[390, 178],[391, 176],[392, 172],[393, 170],[395, 168],[396, 167],[398, 164],[399, 162],[400, 161],[401, 159],[403, 157],[403, 156],[404, 155],[405, 155],[406, 154],[407, 154],[408, 155],[408, 156],[410, 157],[412, 159],[414, 164],[418, 169],[421, 176],[424, 182],[426, 188],[428, 192],[430, 197],[430, 201],[432, 203],[433, 205], [433, 206],[434, 208],[434, 209],[435, 209],[435, 210],[435, 211],[436, 211],[437, 210],[438, 207],[441, 202],[445, 193],[449, 184],[454, 177],[457, 168], [459, 162],[460, 158],[461, 154],[462, 151],[463, 149],[463, 148],[464, 146],[464, 145], [464, 144],[464, 146],[464, 147],[464, 148],[464, 150],[464, 152],[464, 153],[464, 155],[464, 156],[465, 157],[465, 159], [466, 161],[466, 164],[467, 168],[467, 170],[468, 172],[469, 174],[469, 176],[469, 178],[470, 180],[470, 181],[471, 182],[471, 183],[471, 185],[471, 186],[472, 187],[473, 188],[473, 189],[473, 191],[474, 191],[474, 192],[475, 192],[475, 192],[475, 193],[476, 193],[479, 192],[479, 192],[480, 192],[480, 191],[480, 190],[480, 189],[480, 187],[481, 184],[482, 181],[484, 179],[485, 176],[486, 173],[488, 169],[489, 168],[490, 166],[490, 165],[491, 163],[491, 162],[492, 161]]))
        self.templates.append(Template("Line" ,[[430, 158],[435, 156],[440, 156],[447, 154],[456, 153],[465, 151],[476, 150],[484, 149],[495, 148],[503, 148],[510, 148],[517, 148],[520, 148],[525, 148],[530, 148],[533, 148],[535, 149],[538, 149],[539, 149],[540, 149],[541, 149],[542, 149]]))
        '''
    def Recognize(self, points):
        points = resample(points, NumPoints)
        points = rotate_to_zero(points)
        points = scale_to_square(points, SquareSize)
        points = translate_to_origin(points)

        b = float("inf")
        t = None

        for i, temp in enumerate(self.templates):
            Tpoints = temp.points
            d = distance_at_best_angle(points, Tpoints, -AngleRange, AngleRange, AnglePrecision)
            if d < b:
                b = d
                t = temp

        score = 1 - (b / (0.5 * sqrt(SquareSize * SquareSize * 2)))

        if t:
            return Result(t.name, score)
        else:
            return Result('Unrecognized', 0.0)

def average(xs): return sum(xs) / len(xs)


def resample(points, n):
    I = pathlength(points) / float(n-1)
    D = 0
    newPoints = [points[0]]
    i = 1
    while i<len(points):
        p_i = points[i]
        d = distance(points[i-1], p_i)
        if (D + d) >= I:
            qx = points[i-1][0] + ((I-D) / d) * (p_i[0] - points[i-1][0])
            qy = points[i-1][1] + ((I-D) / d) * (p_i[1] - points[i-1][1])
            newPoints.append([qx,qy])
            points.insert(i, [qx,qy])
            D = 0
        else: D = D + d
        i+=1
    return newPoints


def pathlength(points):
    d = 0
    for i,p_i in enumerate(points[:len(points)-1]):
        d += distance(p_i, points[i+1])
    return d


def distance(p1, p2): return float(sqrt((p1[0] - p2[0])**2 + (p1[1] - p2[1])**2))


def centroid(points): return float(average([float(i[0]) for i in points])), float(average([float(i[1]) for i in points]))


def rotate_to_zero(points):
    cx, cy = centroid(points)
    theta = atan2(cy - points[0][1], cx - points[0][0])
    newPoints = rotate_by(points, -theta)
    return newPoints


def rotate_by(points, theta):
    cx, cy = centroid(points)
    newpoints = []
    cos_p, sin_p = cos(theta), sin(theta)
    for p in points:
        qx = (p[0] - cx) * cos_p - (p[1] - cy) * sin_p + cx
        qy = (p[0] - cx) * sin_p + (p[1] - cy) * cos_p + cy
        newpoints.append([qx,qy])
    return newpoints


def bounding_box(points):
    minx, maxx = min((p[0] for p in points)), max((p[0] for p in points))
    miny, maxy = min((p[1] for p in points)), max((p[1] for p in points))
    return minx, miny, maxx-minx, maxy - miny


def scale_to_square(points, size):
    min_x, min_y, w, h = bounding_box(points)
    newPoints = []
    for p in points:
        qx = p[0] * (float(size) / w )
        qy = p[1] * (float(size) / h )
        newPoints.append([qx,qy])
    return newPoints


def translate_to_origin(points):
    cx, cy = centroid(points)
    newpoints = []
    for p in points:
        qx, qy = p[0] - cx , p[1] - cy
        newpoints.append([qx,qy])
    return newpoints

def distance_at_best_angle(points, T, ta, tb, td):
    x1 = Phi * ta + (1 - Phi) * tb
    f1 = distance_at_angle(points, T, x1)
    x2 = (1 - Phi) * ta + Phi * tb
    f2 = distance_at_angle(points, T, x2)
    while abs(tb - ta) > td:
        if f1 < f2:
            tb,x2,f2 = x2, x1, f1
            x1 = Phi * ta + (1 - Phi) * tb
            f1 = distance_at_angle(points, T, x1)
        else:
            ta,x1,f1 = x1, x2, f2
            x2 = (1 - Phi) * ta + Phi * tb
            f2 = distance_at_angle(points, T, x2)
    return min(f1, f2)


def distance_at_angle(points, T, theta):
    newpoints = rotate_by(points, theta)
    d = pathdistance(newpoints, T)
    return d


def pathdistance(a,b):
    d = 0
    for ai, bi in zip(a,b):
        d += distance(ai, bi)
    return d / len(a)
