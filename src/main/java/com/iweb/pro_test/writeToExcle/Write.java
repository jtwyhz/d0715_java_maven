package com.iweb.pro_test.writeToExcle;

import com.iweb.DAO.UserDAO;
import com.iweb.DAO.impl.UserDAOImpl;
import com.iweb.clazzs.Product;
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
public class Write {
    public static void write(String path) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = xssfWorkbook.createSheet("商品信息");
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("product_id");
        row.createCell(1).setCellValue("product_name");
        row.createCell(2).setCellValue("product_price");
        row.createCell(3).setCellValue("stock_num");
        row.createCell(4).setCellValue("sales_num");

        UserDAO userDao = new UserDAOImpl();
        List<Product> products = (ArrayList) userDao.lookProduct();
        if (products != null) {
            for (int i = 0; i < products.size(); i++) {
                XSSFRow row1 = sheet.createRow(i + 1);
                row1.createCell(0).setCellValue(products.get(i).getProduct_id());
                row1.createCell(1).setCellValue(products.get(i).getProduct_name());
                row1.createCell(2).setCellValue(products.get(i).getProduct_price());
                row1.createCell(3).setCellValue(products.get(i).getStock_num());
                row1.createCell(4).setCellValue(products.get(i).getSales_num());
            }
        }
        FileOutputStream outputStream = new FileOutputStream(path + ".xlsx");
        xssfWorkbook.write(outputStream);
        System.out.println("写入成功");
        outputStream.close();
        xssfWorkbook.close();

    }
}
