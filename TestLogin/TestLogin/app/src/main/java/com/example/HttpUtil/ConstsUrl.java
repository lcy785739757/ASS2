package com.example.HttpUtil;


public interface ConstsUrl {

        String ip="192.168.199.249";
        String LOGIN_URL = "http://"+ip+":8080/TestAgain/UserServlet";
        String COURSE_URL = "http://"+ip+":8080/TestAgain/CourseListServlet";
        String BASE_URL = "http://"+ip+":8080/TestAgain/";
        String upLoad_photo_URL = "http://"+ip+":8080/TestAgain/UserServlet?operation=upphoto";//上传头像专用
        String NOTICE_URL = "http://"+ip+":8080/TestAgain/NoticeServlet";//获取通知的url
        String TEST_URL = "http://"+ip+":8080/TestAgain/TestServlet";//获取测试的url
        String BBS_URL = "http://"+ip+":8080/TestAgain/BbsServlet";//师生交流的url
        String RES_URL = "http://"+ip+":8080/TestAgain/ResServlet";//获取课程资源的url
        String HW_URL = "http://"+ip+":8080/TestAgain/HomeworkServlet";//获取作业的url
}
