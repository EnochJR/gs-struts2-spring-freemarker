package com.tom.excel;

import com.tom.BaseTest;
import com.tom.filter.WebConstants;
import com.tom.model.Department;
import com.tom.model.Employee;
import com.tom.model.StyleRowProcessor;
import net.sf.jxls.transformer.Configuration;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * User: tom
 * Date: 14-3-18 下午1:54
 */
public class TestExcel extends BaseTest {
    static final  String DIR=new File(FilenameUtils.getPathNoEndSeparator(WebConstants.class.getClassLoader().getResource("\\").getFile()))+"\\excel\\";
    static final  String TARGET_DIR="F:\\excel\\";
    @Test
    public void testSimpleExport() throws IOException, InvalidFormatException {
        logger.debug("{}",DIR);

        Department department = new Department("IT");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, 12, 2);
        Date d1 = calendar.getTime();
        calendar.set(1980, 2, 15);
        Date d2 = calendar.getTime();
        calendar.set(1976, 7, 20);
        Date d3 = calendar.getTime();
        calendar.set(1968, 5, 6);
        Date d4 = calendar.getTime();
        calendar.set(1978, 8, 17);
        Date d5 = calendar.getTime();
        Employee chief = new Employee("Derek", 35, 3000, 0.30, d1);
        department.setChief(chief);
        Employee elsa = new Employee("Elsa", 28, 1500, 0.15, d2);
        department.addEmployee(elsa);
        Employee oleg = new Employee("Oleg", 32, 2300, 0.25, d3);
        department.addEmployee(oleg);
        Employee neil = new Employee("Neil", 34, 2500, 0.00, d4);
        department.addEmployee(neil);
        Employee maria = new Employee("Maria", 34, 1700, 0.15, d5);
        department.addEmployee(maria);
        Employee john = new Employee("John", 35, 2800, 0.20, d2);
        department.addEmployee(john);
        maria.setSuperior(oleg);
        oleg.setSuperior(john);
        neil.setSuperior(john);
        Map beans = new HashMap();
        beans.put("department", department);
        createExcel(beans,"department.xls");
    }

    private void createExcel(Map beans,String name) throws IOException, InvalidFormatException {
        XLSTransformer transformer = new XLSTransformer();
        transformer.groupCollection("department.staff");
        transformer.getConfiguration().getDigester().setClassLoader(Thread.currentThread().getContextClassLoader());
        String templateFileName = DIR+ name;
        logger.debug("{}", templateFileName);
        String destFileName = TARGET_DIR+ FilenameUtils.getName(templateFileName);
        FileUtils.forceMkdir(new File(destFileName).getParentFile());
        transformer.transformXLS(templateFileName, beans, destFileName);
        File target = new File(destFileName);
        assertTrue("target file " + target.getPath(), target.exists());
    }

    @Test
    public void testMultipleListRowsSample() throws IOException, InvalidFormatException {
        String templateFileName = DIR + "\\WEB-INF\\classes\\test\\resources\\multiplelistrows.xls";
        logger.debug("{}", templateFileName);
        String destFileName = "F:/multiplelistrows_output.xls";
        Department department = new Department("IT");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, 12, 2);
        Date d1 = calendar.getTime();
        calendar.set(1980, 2, 15);
        Date d2 = calendar.getTime();
        calendar.set(1976, 7, 20);
        Date d3 = calendar.getTime();
        calendar.set(1968, 5, 6);
        Date d4 = calendar.getTime();
        calendar.set(1978, 8, 17);
        Date d5 = calendar.getTime();
        Employee chief = new Employee("Derek", 35, 3000, 0.30, d1);
        department.setChief(chief);
        Employee elsa = new Employee("Elsa", 28, 1500, 0.15, d2);
        department.addEmployee(elsa);
        Employee oleg = new Employee("Oleg", 32, 2300, 0.25, d3);
        department.addEmployee(oleg);
        Employee neil = new Employee("Neil", 34, 2500, 0.00, d4);
        department.addEmployee(neil);
        Employee maria = new Employee("Maria", 34, 1700, 0.15, d5);
        department.addEmployee(maria);
        Employee john = new Employee("John", 35, 2800, 0.20, d2);
        department.addEmployee(john);
        maria.setSuperior(oleg);
        oleg.setSuperior(john);
        neil.setSuperior(john);
        Map beans = new HashMap();
        beans.put("department", department);
        XLSTransformer transformer = new XLSTransformer();
        transformer.transformXLS(templateFileName, beans, destFileName);
        File target = new File(destFileName);
        assertTrue("target file " + target.getPath(), target.exists());
    }

    @Test
    public void testSingleListExport() throws IOException, InvalidFormatException {
        String templateFileName = DIR + "\\WEB-INF\\classes\\test\\resources\\employees.xls";
        logger.debug("{}", templateFileName);
        String destFileName = "F:/employees_output.xls";
        Collection staff = new HashSet();
        staff.add(new Employee("Derek", 35, 3000, 0.30));
        staff.add(new Employee("Elsa", 28, 1500, 0.15));
        staff.add(new Employee("Oleg", 32, 2300, 0.25));
        staff.add(new Employee("Neil", 34, 2500, 0.00));
        staff.add(new Employee("Maria", 34, 1700, 0.15));
        staff.add(new Employee("John", 35, 2800, 0.20));
        Map beans = new HashMap();
        beans.put("employee", staff);
        Configuration config = new Configuration();
//        config.setUTF16( true );
        XLSTransformer transformer = new XLSTransformer(config);
        transformer.groupCollection("employee.name");
        transformer.transformXLS(templateFileName, beans, destFileName);
        //transformer.registerCellProcessor( new RedCellProcessor("department.staff") );
        //transformer.registerCellProcessor( new MultPropertyCellProcessor("department.staff", adjProperties ));
        //transformer.registerCellProcessor( new MultPropertyCellProcessor("department.chief", adjProperties));
//        transformer.groupCollection("department.staff");
        File target = new File(destFileName);
        assertTrue("target file " + target.getPath(), target.exists());
    }

    @Test
    public void testHiddenColumnSample() throws IOException, InvalidFormatException {
        String templateFileName =DIR/*DIR*/ + "\\WEB-INF\\classes\\test\\resources\\department.xls";
        logger.debug("{}", templateFileName);
        String destFileName = "F:/hiddencolumn_output.xls";
        Department department = new Department("IT");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, 12, 2);
        Date d1 = calendar.getTime();
        calendar.set(1980, 2, 15);
        Date d2 = calendar.getTime();
        calendar.set(1976, 7, 20);
        Date d3 = calendar.getTime();
        calendar.set(1968, 5, 6);
        Date d4 = calendar.getTime();
        calendar.set(1978, 8, 17);
        Date d5 = calendar.getTime();
        Employee chief = new Employee("Derek", 35, 3000, 0.30, d1);
        department.setChief(chief);
        department.addEmployee(new Employee("Elsa", 28, 1500, 0.15, d2));
        department.addEmployee(new Employee("Oleg", 32, 2300, 0.25, d3));
        department.addEmployee(new Employee("Neil", 34, 2500, 0.00, d4));
        department.addEmployee(new Employee("Maria", 34, 1700, 0.15, d5));
        department.addEmployee(new Employee("John", 35, 2800, 0.20, d2));
        Map beans = new HashMap();
        beans.put("department", department);
        XLSTransformer transformer = new XLSTransformer();
        transformer.setColumnPropertyNamesToHide(new String[]{"age"});
        //transformer.setColumnsToHide(new short[]{1});
        transformer.groupCollection("department.staff");
        transformer.transformXLS(templateFileName, beans, destFileName);
        File target = new File(destFileName);
        assertTrue("target file " + target.getPath(), target.exists());
    }

    @Test
    public void testGroupingSample() throws IOException, InvalidFormatException {
        String templateFileName =DIR + "\\WEB-INF\\classes\\test\\resources\\grouping.xls";
        logger.debug("{}", templateFileName);
        String destFileName = "F:/grouping_output.xls";
        List departments = new ArrayList();
        Department department = new Department("IT");
        Employee chief = new Employee("Derek", 35, 3000, 0.30);
        department.setChief(chief);
        department.addEmployee(new Employee("Elsa", 28, 1500, 0.15));
        department.addEmployee(new Employee("Oleg", 32, 2300, 0.25));
        department.addEmployee(new Employee("Neil", 34, 2500, 0.00));
        department.addEmployee(new Employee("Maria", 34, 1700, 0.15));
        department.addEmployee(new Employee("John", 35, 2800, 0.20));
        departments.add(department);
        department = new Department("HR");
        chief = new Employee("Betsy", 37, 2200, 0.30);
        department.setChief(chief);
        department.addEmployee(new Employee("Olga", 26, 1400, 0.20));
        department.addEmployee(new Employee("Helen", 30, 2100, 0.10));
        department.addEmployee(new Employee("Keith", 24, 1800, 0.15));
        department.addEmployee(new Employee("Cat", 34, 1900, 0.15));
        departments.add(department);
        department = new Department("BA");
        chief = new Employee("Wendy", 35, 2900, 0.35);
        department.setChief(chief);
        department.addEmployee(new Employee("Denise", 30, 2400, 0.20));
        department.addEmployee(new Employee("LeAnn", 32, 2200, 0.15));
        department.addEmployee(new Employee("Natali", 28, 2600, 0.10));
        department.addEmployee(new Employee("Martha", 33, 2150, 0.25));
        departments.add(department);
        Map beans = new HashMap();
        beans.put("departments", departments);
        XLSTransformer transformer = new XLSTransformer();
        transformer.groupCollection("departments0.staff.name");
        transformer.transformXLS(templateFileName, beans, destFileName);
        File target = new File(destFileName);
        assertTrue("target file " + target.getPath(), target.exists());
    }

    @Test
    public void testChartSample() throws IOException, InvalidFormatException {
        String templateFileName =DIR + "\\WEB-INF\\classes\\test\\resources\\chart.xls";
        logger.debug("{}", templateFileName);
        String destFileName = "F:/chart_output.xls";

        List staff = new ArrayList();
        staff.add(new Employee("Derek", 35, 3000, 0.30));
        staff.add(new Employee("Elsa", 28, 1500, 0.15));
        staff.add(new Employee("Oleg", 32, 2300, 0.25));
        staff.add(new Employee("Neil", 34, 2500, 0.00));
        staff.add(new Employee("Maria", 34, 1700, 0.15));
        staff.add(new Employee("John", 35, 2800, 0.20));
        staff.add(new Employee("Leonid", 29, 1700, 0.20));
        Map beans = new HashMap();
        beans.put("employee", staff);
        XLSTransformer transformer = new XLSTransformer();
        transformer.markAsFixedSizeCollection("employee");
        transformer.transformXLS(templateFileName, beans, destFileName);
        File target = new File(destFileName);
        assertTrue("target file " + target.getPath(), target.exists());
    }

    @Test
    public void testAdjacentListsSample() throws IOException, InvalidFormatException {
        String templateFileName =DIR + "\\WEB-INF\\classes\\test\\resources\\adjacentlists.xls";
        logger.debug("{}", templateFileName);
        String destFileName = "F:/adjacentlists_output.xls";


        Department depIT = new Department("IT");

        Employee chief = new Employee("Derek", 35, 3000, 0.30);
        depIT.setChief(chief);
        Employee elsa = new Employee("Elsa", 28, 1500, 0.15);
        depIT.addEmployee(elsa);
        Employee oleg = new Employee("Oleg", 32, 2300, 0.25);
        depIT.addEmployee(oleg);
        Employee neil = new Employee("Neil", 34, 2500, 0.00);
        depIT.addEmployee(neil);
        Employee maria = new Employee("Maria", 34, 1700, 0.15);
        depIT.addEmployee(maria);
        Employee john = new Employee("John", 35, 2800, 0.20);
        depIT.addEmployee(john);

        Department depHR = new Department("HR");

        Employee natali = new Employee("Natali", 25, 1200, 0.1);
        depHR.addEmployee(natali);
        Employee helen = new Employee("Helen", 27, 1100, 0.20);
        depHR.addEmployee(helen);
        Employee olga = new Employee("Olga", 24, 1150, 0.00);
        depHR.addEmployee(olga);

        Map beans = new HashMap();
        beans.put("depIT", depIT);
        beans.put("depHR", depHR);

        XLSTransformer transformer = new XLSTransformer();
        transformer.transformXLS(templateFileName, beans, destFileName);
        File target = new File(destFileName);
        assertTrue("target file " + target.getPath(), target.exists());
    }

    @Test
    public void testStyleRowSample() throws IOException, InvalidFormatException {
        String templateFileName =DIR + "\\WEB-INF\\classes\\test\\resources\\rowstyle.xls";
        logger.debug("{}", templateFileName);
        String destFileName = "F:/rowstyle_output.xls";


        Department department = new Department("IT");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, 12, 2);
        Date d1 = calendar.getTime();
        calendar.set(1980, 2, 15);
        Date d2 = calendar.getTime();
        calendar.set(1976, 7, 20);
        Date d3 = calendar.getTime();
        calendar.set(1968, 5, 6);
        Date d4 = calendar.getTime();
        calendar.set(1978, 8, 17);
        Date d5 = calendar.getTime();
        department.addEmployee(new Employee("Elsa", 28, 1500, 0.15, d1));
        department.addEmployee(new Employee("Oleg", 32, 2300, 0.25, d3));
        department.addEmployee(new Employee("Neil", 34, 2500, 0.00, d4));
        department.addEmployee(new Employee("Maria", 34, 1700, 0.15, d5));
        department.addEmployee(new Employee("John", 35, 2800, 0.20, d2));
        Map beans = new HashMap();
        beans.put("department", department);
        XLSTransformer transformer = new XLSTransformer();
        transformer.registerRowProcessor(new StyleRowProcessor("department.staff"));
        transformer.transformXLS(templateFileName, beans, destFileName);
        File target = new File(destFileName);
        assertTrue("target file " + target.getPath(), target.exists());
    }

    @Test
    public void testBasicTagSample() throws IOException, InvalidFormatException {
        String templateFileName =DIR + "\\WEB-INF\\classes\\test\\resources\\basictags.xls";
        logger.debug("{}", templateFileName);
        String destFileName = "F:/basictags_output.xls";
        List departments = new ArrayList();
        Department department = new Department("IT");
        Employee chief = new Employee("Derek", 35, 3000, 0.30);
        department.setChief(chief);
        department.addEmployee(new Employee("Elsa", 28, 1500, 0.15));
        department.addEmployee(new Employee("Oleg", 32, 2300, 0.25));
        department.addEmployee(new Employee("Neil", 34, 2500, 0.00));
        department.addEmployee(new Employee("Maria", 34, 1700, 0.15));
        department.addEmployee(new Employee("John", 35, 2800, 0.20));
        departments.add(department);
        department = new Department("HR");
        chief = new Employee("Betsy", 37, 2200, 0.30);
        department.setChief(chief);
        department.addEmployee(new Employee("Olga", 26, 1400, 0.20));
        department.addEmployee(new Employee("Helen", 30, 2100, 0.10));
        department.addEmployee(new Employee("Keith", 24, 1800, 0.15));
        department.addEmployee(new Employee("Cat", 34, 1900, 0.15));
        departments.add(department);
        department = new Department("BA");
        chief = new Employee("Wendy", 35, 2900, 0.35);
        department.setChief(chief);
        department.addEmployee(new Employee("Denise", 30, 2400, 0.20));
        department.addEmployee(new Employee("LeAnn", 32, 2200, 0.15));
        department.addEmployee(new Employee("Natali", 28, 2600, 0.10));
        department.addEmployee(new Employee("Martha", 33, 2150, 0.25));
        departments.add(department);
        Map beans = new HashMap();
        beans.put("departments", departments);
        XLSTransformer transformer = new XLSTransformer();
        transformer.transformXLS(templateFileName, beans, destFileName);
        File target = new File(destFileName);
        assertTrue("target file " + target.getPath(), target.exists());
    }
    @Test
    public void testLoopList()throws IOException, InvalidFormatException {
        String templateFileName =DIR + "\\WEB-INF\\classes\\test\\resources\\loopList.xls";
        logger.debug("{}", templateFileName);
        String destFileName = "F:/loopList_output.xls";
        Map<String, Object> beans=new HashMap<String, Object>();
        // fruits
        List<Map<String,String>> fruitList=new ArrayList<Map<String,String>>();

        Map<String,String> fruit=null;
        fruit=new HashMap<String, String>();
        fruit.put("name", "苹果");
        fruit.put("price", "100");
        fruitList.add(fruit);

        fruit=new HashMap<String, String>();
        fruit.put("name", "香蕉");
        fruit.put("price", "200");
        fruitList.add(fruit);
        beans.put("fruits",fruitList);
        XLSTransformer transformer = new XLSTransformer();
        transformer.transformXLS(templateFileName, beans, destFileName);
        File target = new File(destFileName);
        assertTrue("target file " + target.getPath(), target.exists());
    }

}
