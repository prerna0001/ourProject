//package com.stackroute.intelligentservice.service;
//
//import com.opencsv.CSVReader;
//import com.opencsv.CSVReaderBuilder;
//import com.stackroute.intelligentservice.domain.JMeterMatrix;
//import org.apache.jmeter.control.LoopController;
//import org.apache.jmeter.engine.StandardJMeterEngine;
//import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
//import org.apache.jmeter.reporters.ResultCollector;
//import org.apache.jmeter.reporters.Summariser;
//import org.apache.jmeter.save.SaveService;
//import org.apache.jmeter.testelement.TestElement;
//import org.apache.jmeter.testelement.TestPlan;
//import org.apache.jmeter.threads.SetupThreadGroup;
//import org.apache.jmeter.util.JMeterUtils;
//import org.apache.jorphan.collections.HashTree;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.Reader;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class JMeterService {
//
//    public void check(String URL,String JmeterProperties,String JmeterHome) {
//
//        System.out.println("------checking check method of service-1------");
//        //    String url = "http://localhost:8089/api/v1/intelligentService/FSD";
//        System.out.println("------checking check methos of service-77777------");
//        String currentDir = System.getProperty("user.dir");
//        System.out.println("------checking check methos of service-888888------");
//        RestTemplate temp = new RestTemplate();
//
//        Object obj = temp.getForObject(URL, Object.class);
//        System.out.println("objis "+obj);
//
//        System.out.println("------checking check methos of service-9999999999------");
//
//        Map<String, Object> root = (Map) obj;
//        System.out.println("root is "+root);
//        System.out.println("------checking check methos of service-aaaaaaaaa------");
//        Map<String, Object> paths = (Map) root.get("paths");
//        System.out.println(paths);
//        System.out.println("------checking check methos of service-bbbbbbbb------");
//        List<String> keylistpaths = new ArrayList<String>(paths.keySet());
//        System.out.println("------checking check methos of service-100000   `------");
//        String url=root.get("host").toString();
//
//        System.out.println("------checking check method of service-2------");
//
//        CSVReader csvReader = null;
//        Reader reader = null;
//
//        for(int loop=10;loop<1000;loop=loop*10) {
//            List<String> endpoints=keylistpaths;
//            System.out.println("------checking check method of service-3 in loop------");
//            for(String endpoint:endpoints){
//                Map<String, Object> requestsq = paths;
//                Map<String, Object> requestst = (Map) requestsq.get(endpoint);
//                List<String> requestsstring = new ArrayList<String>(requestst.keySet());
//
//                for(String requests:requestsstring){
//                    String finalendpoint = endpoint+url;
//                    StandardJMeterEngine jm = new StandardJMeterEngine();
//                    File file = new File(currentDir + "/intelligent-jMeter.csv");
//                    System.out.println("------checking check methos of service-4 file created???------");
//
//                    if (file.delete()) {
//                        System.out.println(" File deleted");
//                    }
//
//                    else System.out.println("File doesn't exists");
//                    // jmeter.properties
//                    JMeterUtils.loadJMeterProperties(JmeterProperties);    // app.prop
//                    JMeterUtils.initLocale();
//                    JMeterUtils.setJMeterHome(JmeterHome);     //app.prop
//
//                    HashTree hashTree = new HashTree();
//
//                    // HTTP Sampler
//                    HTTPSampler httpSampler = new HTTPSampler();
//                    httpSampler.setDomain(url.split(":")[0]);
//                    httpSampler.setPort(Integer.parseInt(url.split(":")[1]));
//                    httpSampler.setPath(endpoint);
//                    httpSampler.setMethod(requests.toUpperCase());
//
//                    // Loop Controller
//                    TestElement loopCtrl = new LoopController();
//                    ((LoopController) loopCtrl).setLoops(1);
//                    ((LoopController) loopCtrl).addTestElement(httpSampler);
//                    ((LoopController) loopCtrl).setFirst(true);
//
//                    // Thread Group
//                    SetupThreadGroup threadGroup = new SetupThreadGroup();
//                    threadGroup.setNumThreads(loop);
//
//                    threadGroup.setSamplerController((LoopController) loopCtrl);
//
//                    // Test plan
//                    TestPlan testPlan = new TestPlan("MY TEST PLAN");
//
//                    hashTree.add("testPlan", testPlan);
//                    hashTree.add("loopCtrl", loopCtrl);
//                    hashTree.add("threadGroup", threadGroup);
//                    hashTree.add("httpSampler", httpSampler);
//
//                    String slash = System.getProperty("file.separator");
//                    HashTree testPlanTree = new HashTree();
//                    testPlanTree.add(testPlan);
//                    HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
//                    threadGroupHashTree.add(httpSampler);
//
//                    try {
//                        SaveService.saveTree(testPlanTree, new FileOutputStream(currentDir + "/intelligent-jMeter.jmx"));
//                    }
//
//                    catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    Summariser summer = null;
//                    String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
//
//                    if (summariserName.length() > 0) {
//                        summer = new Summariser(summariserName);
//                    }
//
//                    Map<String, String> values;
//                    String logFile = currentDir + "/intelligent-jMeter.csv";
//                    ResultCollector logger = new ResultCollector(summer);
//                    logger.setFilename(logFile);
//
//                    testPlanTree.add(testPlanTree.getArray()[0], logger);
//                    jm.configure(testPlanTree);
//                    jm.run();
//                    String SAMPLE_CSV_FILE_PATH = currentDir + "/intelligent-jMeter.csv";
//
//
//
//                    try {
//                        reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
//                        csvReader = new CSVReader(reader);
//
//
//                    } catch (Exception e) {
//                        csvReader = new CSVReaderBuilder(reader).build();
//                    }
//
//                    int i = 0;
//                    ArrayList<Integer> store = new ArrayList<Integer>();
//                    try {
//                        String[] nextRecord;
//
//                        while ((nextRecord = csvReader.readNext()) != null) {
//                            if (i > 0) {
//
//                                store.add(Integer.valueOf(nextRecord[1]));
//                            }
//                            i++;
//                        }
//                    } catch (Exception e2) {
//                        e2.printStackTrace();
//                    }
//                    System.out.println(store);
//
//                    int number = 0;
//
//                    for (int k = 0; k < store.size() - 1; k++) {
//                        number = number + store.get(k);
//                    }
//
//                    int size=number/(store.size()-1);
//
//                    JMeterMatrix jMeterMatrix=new JMeterMatrix();
//                    jMeterMatrix.setRequestMethod(requests.toUpperCase());
//                    jMeterMatrix.setRequestUrl(httpSampler.getDomain());
//                    jMeterMatrix.setResponseTime(size);
//                    jMeterMatrix.setServerPort(httpSampler.getPort());
//                    jMeterMatrix.setEndpoint(endpoint);
//
//
//                }
//            }
//        }
//    }
//}
