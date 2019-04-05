package com.united.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.library.Base;
import com.library.DatePicker;

public class TestingDatePicker extends Base{
	@Test
    public void testing1() {
        try {
            driver.get("http://www.united.com/");
            WebElement from = driver.findElement(By.id("bookFlightOriginInput"));
            from.sendKeys("washing");
            Thread.sleep(3 * 1000);
            from.sendKeys(Keys.ARROW_DOWN);
            from.sendKeys(Keys.ARROW_DOWN);
            from.sendKeys(Keys.ARROW_DOWN);
            from.sendKeys(Keys.ENTER);
            driver.findElement(By.id("DepartDate")).click();
            Thread.sleep(1 * 1000);
            DatePicker datePicker = new DatePicker();
            datePicker.calculateTime("01/30/2019");           
            selectDate(datePicker);
            datePicker.calculateTime("02/28/2019");
            selectDate(datePicker);
        
            Thread.sleep(10 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ////////// helper method
    public void selectDate(DatePicker datePicker) {
        // find correct calendar page
        int numberClicks = datePicker.getNumberClicks();
        if (numberClicks > 0) {
            String iconsCssString = ".DayPickerNavigation_container.DayPickerNavigation_container_1.DayPickerNavigation_container__horizontal.DayPickerNavigation_container__horizontal_2";
            WebElement parent = driver.findElement(By.cssSelector(iconsCssString));
            List<WebElement> icons = parent.findElements(By.tagName("span"));
            //System.out.println("icons size: " + icons.size());
            WebElement rightIcon = icons.get(1);
            for (int i = 1; i <= numberClicks; i++) {
                rightIcon.click();
            }
        }
        //Thread.sleep(1 * 1000);
        String calendarCssString = ".CalendarMonthGrid_month__horizontal.CalendarMonthGrid_month__horizontal_1";
        List<WebElement> calendarElems = driver.findElements(By.cssSelector(calendarCssString));
        WebElement fromCal = calendarElems.get(1);
        WebElement retuCal = calendarElems.get(2);
        String monthTxt = fromCal.findElement(By.tagName("strong")).getText();
        String reMonthTxt = retuCal.findElement(By.tagName("strong")).getText();
        //System.out.println("monthTxt: " + monthTxt);
        //System.out.println("renthTxt: " + monthTxt);
        
        // Search departure
        if (monthTxt.contains(datePicker.getFutureMonth())) {
            List<WebElement> dayRows = fromCal.findElements(By.tagName("tr"));
            boolean isExit = false;
            for (WebElement row : dayRows) {
                List<WebElement> days = row.findElements(By.tagName("td"));
                for (WebElement day : days) {                                       
                    if (!day.getText().isEmpty()) {                 
                        int selDayTxt = Integer.parseInt((day.getText()));
                        //System.out.println("targetDate: " + selDayTxt);
                        if (datePicker.getFutureDay() == selDayTxt) {
                            // check if date is blocked or not first
                            String classAttriTxt = day.getAttribute("class");
                            if(classAttriTxt.contains("CalendarDay__blocked_out_of_range"))
                            {
             System.out.println("Sorry, ["+datePicker.getUserSelectedDate()+"] date you would like to select is not available in the calendar at this moment.");
                                System.out.println("Please try another date.");
                                isExit = true;
                                break;
                            }
                            day.click();
                            //System.out.println("targetDate: " + selDayTxt);
                            isExit = true;
                            break;
                        }
                    }
                }
                if (isExit) {
                    break;
                }
            }
        }// Search return 
        else if (reMonthTxt.contains(datePicker.getFutureMonth())) {
            List<WebElement> dayRows = retuCal.findElements(By.tagName("tr"));
            boolean isExit = false;
            for (WebElement row : dayRows) {
                List<WebElement> days = row.findElements(By.tagName("td"));
                for (WebElement day : days) {
                    if (!day.getText().isEmpty()) {                     
                        int selDayTxt = Integer.parseInt((day.getText()));
                        //System.out.println("targetDate: " + selDayTxt);
                        if (datePicker.getFutureDay() == selDayTxt) {
                            // check if date is blocked or not first
                            String classAttriTxt = day.getAttribute("class");
                            if(classAttriTxt.contains("CalendarDay__blocked_out_of_range"))
                            {
                                System.out.println("Sorry, ["+datePicker.getUserSelectedDate()+"] date you would like to select is not available in the calendar at this moment.");
                                System.out.println("Please try another date.");
                                isExit = true;
                                break;
                            }
                            day.click();
                            //System.out.println("targetDate: " + selDayTxt);
                            isExit = true;
                            break;
                        }
                    }
                }
                if (isExit) {
                    break;
                }
            }
        }
    }
}
