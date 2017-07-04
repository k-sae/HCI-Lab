#include "opencv2/core/core.hpp"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include "iostream"
#include "guiclass.h"
using namespace cv;
using namespace std;
int main() 
{
	
	Mat image = imread("newback.png", CV_LOAD_IMAGE_COLOR);
	Rect button,button2;
	string buttonText("play as cop");
	string buttonText2("play as theif");
    button= Rect(0,500,image.cols, 50);
	button2 = Rect(0, 600, image.cols, 50);
	image(button) = Vec3b(200,200,200);
	image(button2) = Vec3b(200, 200, 200);
	putText(image, buttonText, Point(button.width*0.40, 500+button.height*0.5), FONT_HERSHEY_PLAIN, 1, Scalar(0, 0, 0));
	putText(image, buttonText2, Point(button.width*0.40,600 + button.height*0.5), FONT_HERSHEY_PLAIN, 1, Scalar(0, 0, 0));
	namedWindow("Display window", CV_WINDOW_AUTOSIZE);
	imshow("Display window", image);
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
	return 0;*/