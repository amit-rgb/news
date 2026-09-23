package com.bheemnagartimes.news.model;
import jakarta.validation.constraints.NotBlank;
public class NewsRequest {
@NotBlank(message="कृपया कच्ची खबर दर्ज करें।") private String rawNews; private String date; private String location; private String section;
public String getRawNews(){return rawNews;} public void setRawNews(String v){rawNews=v;} public String getDate(){return date;} public void setDate(String v){date=v;} public String getLocation(){return location;} public void setLocation(String v){location=v;} public String getSection(){return section;} public void setSection(String v){section=v;}
}