package com.stackroute.SeedData;
import com.stackroute.domain.Idea;
import com.stackroute.repository.IdeaHubRepository;
import com.stackroute.service.IdeaHubService;
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
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    IdeaHubRepository ideaHubRepository;
    @Autowired
    IdeaHubService ideaHubService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        try {

            FileInputStream file = new FileInputStream(new File("Seed_Ideas1.xlsx"));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            // Traversing over each row of XLSX file
//            rowIterator.next();//Skipping 1st line
           while (rowIterator.hasNext()) {

               Row row = rowIterator.next();

               // For each row, iterate through each columns
               Iterator<Cell> cellIterator = row.cellIterator();
               for (int i = 1; cellIterator.hasNext(); i++) {
                   Idea ideas = new Idea();
                   for (int j = 0; j <= sheet.getLeftCol(); j++) {


                       ideas.setTitle(workbook.getSheetAt(0).getRow(i).getCell(j + 0).toString());
                       ideas.setDomain(workbook.getSheetAt(0).getRow(i).getCell(j + 1).toString());
                       ideas.setSubDomain(workbook.getSheetAt(0).getRow(i).getCell(j + 2).toString());
                       ideas.setDescription(workbook.getSheetAt(0).getRow(i).getCell(j + 3).toString());
                       //idea1.setBudget(Double.parseDouble(workbook.getSheetAt(0).getRow(i).getCell(j+4)+"\n"));
                       ideas.setEmailId(workbook.getSheetAt(0).getRow(i).getCell(j + 7).toString());
                       ideas.setRoles(List.of(workbook.getSheetAt(0).getRow(i).getCell(j + 5).toString().split(",")));
                       ideaHubRepository.save(ideas);
                       System.out.println(ideas);
                       ideaHubService.send(ideas);

                   }

               }
           }

                 file.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}



