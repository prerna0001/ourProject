package com.stackroute.serviceproviderprofileservice.SeedData;

import com.stackroute.serviceproviderprofileservice.domain.ServiceProvider;
import com.stackroute.serviceproviderprofileservice.repository.ServiceProviderRepository;
import com.stackroute.serviceproviderprofileservice.service.ServiceProviderService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent>{
        @Autowired
    ServiceProviderRepository serviceProviderRepository;
        @Autowired
    ServiceProviderService serviceProviderService;
        @Override
        public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

            try {

                FileInputStream file = new FileInputStream(new File("Seed_Ideas2.xlsx"));

                XSSFWorkbook workbook = new XSSFWorkbook(file);

                XSSFSheet sheet = workbook.getSheetAt(0);

                Iterator<Row> rowIterator = sheet.iterator();

//            rowIterator.next();//Skipping 1st line
                while (rowIterator.hasNext()) {

                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    for (int i = 1; cellIterator.hasNext(); i++) {
                         ServiceProvider service=new ServiceProvider();
                        for (int j = 0; j <= sheet.getLeftCol(); j++) {
                            service.setEmailId(workbook.getSheetAt(0).getRow(i).getCell(j + 1).toString());
                            service.setPassword(workbook.getSheetAt(0).getRow(i).getCell(j + 2).toString());
                            service.setName(workbook.getSheetAt(0).getRow(i).getCell(j + 3).toString());
                            service.setDomain(workbook.getSheetAt(0).getRow(i).getCell(j + 4).toString());
                            service.setSubDomain(List.of(workbook.getSheetAt(0).getRow(i).getCell(j + 5).toString()));
                            service.setRole(workbook.getSheetAt(0).getRow(i).getCell(j + 6).toString());
                            service.setSkills(List.of(workbook.getSheetAt(0).getRow(i).getCell(j + 7).toString().split(",")));
                            service.setAbout(workbook.getSheetAt(0).getRow(i).getCell(j + 8).toString());
//                            service.setChargePerHour(workbook.getSheetAt(0).getRow(i).getCell(j + 9).toString());

                            serviceProviderRepository.save(service);
                            System.out.println(service);
                            serviceProviderService.send(service);

                        }

                    }
                }

                file.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

