#include "opencv2/core/core.hpp"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include <iostream>
#include "GeometricRecognizer.h"
#include<Windows.h>
#include <thread>
#include "GeometricRecognizer.h"
#include <algorithm> 
#include <iostream>
using namespace cv;
using namespace std;
using namespace DollarRecognizer;
class theif {
	int locx;
	int locy;
	Mat img = imread("theif.png",0);
	//int hp for later
public:
	Mat getimg() {
		return this->img;
	}
	void setlocx(int locx) {
		this->locx = locx;
	
	}
	void setlocy(int locy){
		this->locy = locy;
	}
	int getlocx(){
		return this->locx;
	}
	int getlocy() {
		return this->locy;
	}
};

Mat image;
string winName = "desplay window";
Rect button, button2,button3;
int score = 0;
vector<theif>myvector;

void Startgame() {
	Mat frame;
	double minVal;
	double maxVal;
	Point minLoc;
	Point maxLoc;
	Mat gray;
	int level = 1;
	int score = 0;
	VideoCapture cap(0);
	int width = cap.get(3);
	int	height = cap.get(4);
	theif the;
	
	for (int i = 0; i < level; i++) {
		the.setlocx(rand() % (width - 75 + 1));
		the.setlocy(rand() % (height- 75 + 1));
		myvector.push_back(the);

	}
	if (!cap.isOpened())
	{
		cout << "Cannot open the web cam" << endl;
	}
	while (true)
	{
		bool bSuccess = cap.read(frame);
		if (!bSuccess)
		{
			cout << "Cannot read a frame from video stream" << endl;
			break;
		}
		cvtColor(frame, gray, CV_BGR2GRAY);
		minMaxLoc(gray, &minVal, &maxVal, &minLoc, &maxLoc);
		Mat dark;
		threshold(gray, dark, 255, 255, 3);
		Mat theifimg = myvector[0].getimg();
		for (int i = 0; i < myvector.size(); i++) {
theifimg.copyTo(dark(Rect(myvector[i].getlocx(), myvector[i].getlocy(), theifimg.cols, theifimg.rows)));
		}	//	cvtColor(frame, frame, CV_BGR2GRAY);
		string livesStr = " score " + to_string(score) + " level" + to_string(level);
		putText(dark,
			livesStr,
			Point(150, 75), // Coordinates
			FONT_HERSHEY_PLAIN, // Font
			2.0, // Scale. 2.0 = 2x bigger
			Scalar(255, 0, 0), // Color
			1, // Thickness
			CV_AA);
		if (maxVal > 240) {
			circle(dark, maxLoc, 5, (0, 0, 255), 2);
			for (int i = 0; i < myvector.size(); i++) {
				if (maxLoc.x < (myvector[i].getlocx() + (.5 * 75)) && maxLoc.x >(myvector[i].getlocx() - (.5 * 75)) &&
					maxLoc.y < (myvector[i].getlocy() + (.5 * 75)) && maxLoc.y >(myvector[i].getlocy() - (.5 * 75))) {
					myvector.erase(myvector.begin() + i);
					score++;
				}
			}
		}
		imshow("displaywindow", dark);
		if (myvector.size() == 0) {
			level += 1;
			for (int i = 0; i < level; i++) {
				the.setlocx(rand() % (width - 75 + 1));
				the.setlocy(rand() % (height - 75 + 1));
				myvector.push_back(the);

			}
		}
		if (waitKey(30) == 27 || level == 10)
		{
			cout << "esc key is pressed by user" << score << endl;
			break;
		}
	}
	waitKey(0);

}
void circlemode() {
	Mat frame;
	double minVal;
	double maxVal;
	Point minLoc;
	Point maxLoc;
	Mat gray;
	int level = 1;
	int score = 0;
	theif the;
	VideoCapture stream(0);
	int width = stream.get(3);
	int	height = stream.get(4);
	if (!stream.isOpened()) { //check if video device has been initialised
		cout << "cannot open camera";
	}

	for (int i = 0; i < level; i++) {
		the.setlocx(rand() % (width - 75 + 1));
		the.setlocy(rand() % (height - 75 + 1));
		myvector.push_back(the);

	}
	Path2D path;
	GeometricRecognizer geo;
	geo.loadTemplates();
	int size;
	bool laser = false;
	while (true) {
		if (!laser)
			path.clear();
		stream.read(frame);
		cvtColor(frame, gray, CV_BGR2GRAY);
		minMaxLoc(gray, &minVal, &maxVal, &minLoc, &maxLoc);
		Mat dark;
		threshold(gray, dark, 255, 255, 3);
		Mat theifimg = myvector[0].getimg();
		for (int i = 0; i < myvector.size(); i++) {
			theifimg.copyTo(dark(Rect(myvector[i].getlocx(), myvector[i].getlocy(), theifimg.cols, theifimg.rows)));
		}
		string livesStr = " score " + to_string(score) + " level" + to_string(level);
		putText(dark,
			livesStr,
			Point(150, 75), // Coordinates
			FONT_HERSHEY_PLAIN, // Font
			2.0, // Scale. 2.0 = 2x bigger
			Scalar(255, 0, 0), // Color
			1, // Thickness
			CV_AA);
		if (maxVal >= 254) {
			path.push_back(Point2D(maxLoc.x, maxLoc.y));
			laser = true;
		}
		else if (maxVal < 254) {
			laser = false;
		}
		Point x, y;
		size = path.size();
		for (int i = 0; i < size - 1; i++) {
			path[i].x;
			path[i].y;
			if (size >= 2)
			{
				x.x = path[i].x;
				x.y = path[i].y;
				y.x = path[i + 1].x;
				y.y = path[i + 1].y;
				line(dark, x, y, (255, 255, 255), 1, 4, 0);
			}

		}
		if (geo.recognize(path).name == "circle"&&path.size()>0) {
			x.x = path[0].x;
			y.y = path[0].y;
			double maxD = 0;
			Point maxp;
			for (int i = 1; i < size - 1; i++) {
				if (sqrt(pow(path[i].x - path[0].x, 2) + pow(path[i].x - path[0].x, 2)) > maxD) {
					maxD = sqrt(pow(path[i].x - path[0].x, 2) + pow(path[i].x - path[0].x, 2));
					maxp.x = path[i].x;
					maxp.y = path[i].y;
				}
			}
			Point center;
			center.x = (maxp.x + path[0].x) / 2;
			center.y = (maxp.y + path[0].y) / 2;
			for (int i = 0; i < myvector.size(); i++) {
				if (center.x < (myvector[i].getlocx() + (.5 * 75)) && center.x >(myvector[i].getlocx() - (.5 * 75)) &&
					center.y < (myvector[i].getlocy() + (.5 * 75)) && center.y >(myvector[i].getlocy() - (.5 * 75))) {
					myvector.erase(myvector.begin() + i);
					score++;
				}
			}
		}
		if (myvector.size() == 0) {
			level += 1;
			for (int i = 0; i < level; i++) {
				the.setlocx(rand() % (width - 75 + 1));
				the.setlocy(rand() % (height - 75 + 1));
				myvector.push_back(the);

			}
		}
		if (!path.empty() && !laser)
			cout << geo.recognize(path).name << endl;
		imshow("cam", dark);
		if (waitKey(10) == 27 || level == 10) break;	 // Esc button
	}
}
void on_mouse(int e, int x, int y, int d, void *ptr)
{
	Point*p = (Point*)ptr;
	p->x = x;
	p->y = y;
}
void mousemode() {
	Mat frame;
	double minVal;
	double maxVal;
	Point minLoc;
	Point maxLoc;
	Mat gray;
	int level = 1;
	int score = 0;
	VideoCapture cap(0);
	int width = cap.get(3);
	int	height = cap.get(4);
	theif the;
	Point p;

	for (int i = 0; i < level; i++) {
		the.setlocx(rand() % (width - 75 + 1));
		the.setlocy(rand() % (height - 75 + 1));
		myvector.push_back(the);

	}
	if (!cap.isOpened())
	{
		cout << "Cannot open the web cam" << endl;
	}
	while (true)
	{
		bool bSuccess = cap.read(frame);
		if (!bSuccess)
		{
			cout << "Cannot read a frame from video stream" << endl;
			break;
		}
		cvtColor(frame, gray, CV_BGR2GRAY);
		Mat dark;
		threshold(gray, dark, 255, 255, 3);
		Mat theifimg = myvector[0].getimg();
		for (int i = 0; i < myvector.size(); i++) {
			theifimg.copyTo(dark(Rect(myvector[i].getlocx(), myvector[i].getlocy(), theifimg.cols, theifimg.rows)));
		}	//	cvtColor(frame, frame, CV_BGR2GRAY);
		string livesStr = " score " + to_string(score) + " level" + to_string(level);
		putText(dark,
			livesStr,
			Point(150, 75), // Coordinates
			FONT_HERSHEY_PLAIN, // Font
			2.0, // Scale. 2.0 = 2x bigger
			Scalar(255, 0, 0), // Color
			1, // Thickness
			CV_AA);
		setMouseCallback("displaywindow", on_mouse, &p);
			for (int i = 0; i < myvector.size(); i++) {
				if (p.x < (myvector[i].getlocx() + (.5 * 75)) && p.x >(myvector[i].getlocx() - (.5 * 75)) &&
					p.y < (myvector[i].getlocy() + (.5 * 75)) && p.y >(myvector[i].getlocy() - (.5 * 75))) {
					myvector.erase(myvector.begin() + i);
					score++;
				}
			}
		
		
		imshow("displaywindow", dark);
		if (myvector.size() == 0) {
			level += 1;
			for (int i = 0; i < level; i++) {
				the.setlocx(rand() % (width - 75 + 1));
				the.setlocy(rand() % (height - 75 + 1));
				myvector.push_back(the);

			}
		}
		if (waitKey(30) == 27 || level == 10)
		{
			cout << "esc key is pressed by user" << score << endl;
			break;
		}
	}
	waitKey(0);

}
void callBackFunc(int event, int x, int y, int flags, void* userdata)
{
	if (y > 480 && y < 530)
	{
		if (event == EVENT_LBUTTONDOWN)
		{
			if (button.contains(Point(x, y)))
			{
				cout << "Clicked!" << endl;
				rectangle(image, button, Scalar(0, 0, 255), 2);
				Startgame();
			}
		}
		if (event == EVENT_LBUTTONUP)
		{
			rectangle(image, button, Scalar(200, 200, 200), 2);
		}

		imshow(winName, image);
		waitKey(1);
	}if (y > 550 && y < 600) {
		if (event == EVENT_LBUTTONDOWN)
		{
			if (button2.contains(Point(x, y)))
			{
				cout << "Clicked!" << endl;
				rectangle(image, button2, Scalar(0, 0, 255), 2);
				mousemode();
			}
		}
		if (event == EVENT_LBUTTONUP)
		{
			rectangle(image, button2, Scalar(200, 200, 200), 2);
		}
		waitKey(1);
	}
	if (y > 620 && y < 680) {
		if (event == EVENT_LBUTTONDOWN)
		{
			if (button3.contains(Point(x, y)))
			{
				cout << "Clicked!" << endl;
				rectangle(image, button3, Scalar(0, 0, 255), 2);
				circlemode();
			}
		}
		if (event == EVENT_LBUTTONUP)
		{
			rectangle(image, button2, Scalar(200, 200, 200), 2);
		}
		waitKey(1);
	}
}

int main()
{

	image = imread("newback.png", CV_LOAD_IMAGE_COLOR);
	string buttonText("play as cop");
	string buttonText2("play as cop with mouse");
	string buttonText3("play with laser but with circles");
	button = Rect(0, 480, image.cols, 50);
	button2 = Rect(0, 550, image.cols, 50);
	button3 = Rect(0, 620, image.cols, 50);
	image(button) = Vec3b(200, 200, 200);
	image(button2) = Vec3b(200, 200, 200);
	image(button3) = Vec3b(200, 200, 200);
	putText(image, buttonText, Point(button.width*0.4, 480 + button.height*0.5), FONT_HERSHEY_PLAIN, 1, Scalar(0, 0, 0));
	putText(image, buttonText2, Point(button.width*0.4, 550 + button.height*0.5), FONT_HERSHEY_PLAIN, 1, Scalar(0, 0, 0));
	putText(image, buttonText3, Point(button.width*0.4, 620 + button.height*0.5), FONT_HERSHEY_PLAIN, 1, Scalar(0, 0, 0));
	namedWindow(winName, CV_WINDOW_AUTOSIZE);
	namedWindow(winName);
	setMouseCallback(winName, callBackFunc);
	imshow(winName, image);
	waitKey();
	return 0;
}
// TODO 
//1/ start menu
//2/  create class draw shooting (cops// thieives)
//3/  git poistion of lazer and remove them when lazer touch 
//4/ add 3 points in class (head/body/chest) to kill
//5/ add by random and lvls 
//6/ add theives mode 
// how we gona work we will make windows as if  its wall  when  we touch postion relative in screen the target vanish 
/*
// capture lazer for later
Mat image;
double minVal;
double maxVal;
Point minLoc;
Point maxLoc;
Mat gray;
VideoCapture cap(0);
if (!cap.isOpened())
{
cout << "Cannot open the web cam" << endl;
return -1;
}
while (true)
{
bool bSuccess = cap.read(image);
if (!bSuccess)
{
cout << "Cannot read a frame from video stream" << endl;
break;
}
cvtColor(image, gray, CV_BGR2GRAY);
minMaxLoc(gray, &minVal, &maxVal, &minLoc, &maxLoc);
circle(image, maxLoc, 5, (255, 0, 0), 2);
imshow("displaywindow", image);
if (waitKey(30) == 27)
{
cout << "esc key is pressed by user" << endl;
break;
}
}
waitKey(0);
return 0;
			}*/