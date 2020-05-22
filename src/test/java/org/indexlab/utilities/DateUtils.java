package org.indexlab.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public boolean isDateTimeGeneratedSelectedFormat(List<String> actualDates,String selectedFormat) {
        SimpleDateFormat format = new SimpleDateFormat(selectedFormat);
        try {
            format.parse(actualDates.get(0));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isGeneratedDateTimeWithinGivenRange(List<String> actualDates,String selectedFormat,String startDate,String endDate) {
        SimpleDateFormat format = new SimpleDateFormat(selectedFormat);
        SimpleDateFormat startEndDateFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
        boolean status;

        List<Date> dates = new ArrayList<>();
        try {
            for(String date : actualDates) {
                dates.add(format.parse(date));
            }
        } catch (ParseException e) {
            throw new RuntimeException("Date in String format is not able to parse");
        }

        dates.sort(Comparator.naturalOrder());

        try {
            Date selectedStartDate = startEndDateFormat.parse(startDate);
            Date selectedEndDate = startEndDateFormat.parse(endDate);
            status = dates.get(0).after(selectedStartDate) && dates.get(dates.size()-1).before(selectedEndDate);
        } catch (ParseException e) {
            throw new RuntimeException("Date in String format is not able to parse");
        }

        return status;
    }
}
