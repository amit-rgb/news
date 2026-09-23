package com.bheemnagartimes.news.service;
import com.bheemnagartimes.news.model.NewsArticle;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.*;
import java.nio.file.*;
import java.util.Base64;

@Service public class PdfService{
 private final TemplateEngine engine; private final Path uploadDir;
 public PdfService(TemplateEngine e,@Value("$"+"{news.upload-dir:./data/uploads}")String dir){engine=e;uploadDir=Paths.get(dir).toAbsolutePath().normalize();}
 public byte[] render(NewsArticle a,String brand,String tagline,String location,String slogan)throws IOException{
  Context c=new Context();c.setVariable("article",a);c.setVariable("brand",brand);c.setVariable("tagline",tagline);c.setVariable("location",location);c.setVariable("slogan",slogan);
  String html=engine.process("newspaper",c);
  if(a.getMainImage()!=null&&a.getMainImage().startsWith("/uploads/")){String name=a.getMainImage().substring("/uploads/".length());Path p=uploadDir.resolve(name).normalize();if(p.startsWith(uploadDir)&&Files.exists(p)){String mime=Files.probeContentType(p);if(mime==null)mime="image/jpeg";String data="data:"+mime+";base64,"+Base64.getEncoder().encodeToString(Files.readAllBytes(p));html=html.replace(a.getMainImage(),data);}}
  try(ByteArrayOutputStream out=new ByteArrayOutputStream()){PdfRendererBuilder b=new PdfRendererBuilder();b.useFastMode();File regular=new File("/usr/share/fonts/truetype/noto/NotoSansDevanagari-Regular.ttf");File bold=new File("/usr/share/fonts/truetype/noto/NotoSansDevanagari-Bold.ttf");if(regular.exists())b.useFont(regular,"NotoDevanagari");if(bold.exists())b.useFont(bold,"NotoDevanagari",700,com.openhtmltopdf.outputdevice.helper.FontStyle.NORMAL);b.withHtmlContent(html,"http://localhost/");b.toStream(out);b.run();return out.toByteArray();}
 }
}