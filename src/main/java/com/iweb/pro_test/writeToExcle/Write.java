package com.iweb.pro_test.readAndWrite;


import com.iweb.DAO.UserDAO;
import com.iweb.DAO.impl.UserDAOImpl;
import com.iweb.clazzs.User;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author tang
 * @date 2023/7/14 18:50
 */
//public class Write {
//    public static void write(String path) throws IOException {
//        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
//        XSSFSheet sheet = xssfWorkbook.createSheet("商品信息");
//        XSSFRow row = sheet.createRow(0);
//        row.createCell(0).setCellValue("id");
//        row.createCell(1).setCellValue("name");
//        UserDAO userDao = new UserDAOImpl();
//        List<User> users = (ArrayList) userDao.lookProduct();
//        if (users != null) {
//            for (int i = 0; i < users.size(); i++) {
//                XSSFRow row1 = sheet.createRow(i + 1);
//                row1.createCell(0).setCellValue(users.get(i).getId());
//                row1.createCell(1).setCellValue(users.get(i).getName());
//            }
//        }
//        FileOutputStream outputStream = new FileOutputStream(path + ".xlsx");
//        xssfWorkbook.write(outputStream);
//        System.out.println("写入成功");
//        outputStream.close();
//        xssfWorkbook.close();
//
//    }
//}
