#include "opencv2/core/core.hpp"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include "iostream"
#include "guiclass.h"
using namespace cv;
using namespace std;

class theif{

};


Mat image;
string winName = "desplay window";
Rect button, button2;
int level=0;
void Startgame() {
	Mat frame;
	double minVal;
	double maxVal;
	Point minLoc;
	Point maxLoc;
	Mat gray;
	VideoCapture cap(0);
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
		circle(frame, maxLoc, 5, (255, 0, 0), 2);
		imshow("displaywindow", frame);
		if (waitKey(30) == 27)
		{
			cout << "esc key is pressed by user" << endl;
			break;
		}
	}
	waitKey(0);
}
void callBackFunc(int event, int x, int y, int flags, void* userdata)
{
	if ( y > 500 && y < 550)
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
	}if (y > 600 && y < 650) {
		if (event == EVENT_LBUTTONDOWN)
		{
			if (button2.contains(Point(x, y)))
			{
				cout << "Clicked!" << endl;
				rectangle(image, button2, Scalar(0, 0, 255), 2);
			}
		}
		if (event == EVENT_LBUTTONUP)
		{
			rectangle(image, button2, Scalar(200, 200, 200), 2);
		}

		imshow(winName, image);
		waitKey(1);
	}
}
	
int main() 
{
	
 image = imread("newback.png", CV_LOAD_IMAGE_COLOR);
	string buttonText("play as cop");
	string buttonText2("play as theif");
    button= Rect(0,500,image.cols, 50);
	button2 = Rect(0, 600, image.cols, 50);
	image(button) = Vec3b(200,200,200);
	image(button2) = Vec3b(200, 200, 200);
	putText(image, buttonText, Point(button.width*0.40, 500+button.height*0.5), FONT_HERSHEY_PLAIN, 1, Scalar(0, 0, 0));
	putText(image, buttonText2, Point(button.width*0.40,600 + button.height*0.5), FONT_HERSHEY_PLAIN, 1, Scalar(0, 0, 0));
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
	return 0;*/