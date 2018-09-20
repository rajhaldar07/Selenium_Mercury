package com.parchment.Actions;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jcraft.jsch.ChannelSftp;
import com.parchment.Util.FileAccess;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateFile {

    private static final Logger logger = LoggerFactory.getLogger(CreateFile.class);

    private String txtTranscriptFileName;

    public String getTxtTranscriptFileName() {
        return txtTranscriptFileName;
    }

    public void setTxtTranscriptFileName(String txtTranscriptFileName) {
        this.txtTranscriptFileName = txtTranscriptFileName;
    }

    public boolean pdf(String studentFirstName, String studentLastName, String studentDob, String remoteIp) {
     return pdf(studentFirstName.concat(" ").concat(studentLastName), studentDob, remoteIp);
    }

    public boolean pdf(String studentName, String studentDob, String remoteIp) {
        ChannelSftp channelSftp = new FileAccess().getChannelSftp(remoteIp);
        try{
            Document doc = new Document();
            File file = new File("TS.pdf");
            PdfWriter.getInstance(doc, new FileOutputStream(file));
            doc.open();
            Paragraph paragraph = new Paragraph();
            Font font = FontFactory.getFont("Arial", 10);
            paragraph.setFont(font);
            paragraph.add(
                    new StringBuilder().append("Student: ").append(studentName).append("\r\nDOB: ").append(studentDob)
                            .append("\r\nID: \r\n").append("Address: 123 Main Street Guardian:\r\n")
                            .append("Tempe, AZ 85221\r\n").append("Graduation/Leave Year: 2014\r\n")
                            .append("Principal: John Miller\r\n")
                            .append("Completed US Constitution State Constitution Drivers Ed Consumer Ed PSAE\r\n")
                            .append("Cred Fall 2010-2011\r\n").append("Grade: 10-Sandwich High School\r\n")
                            .append("Course Grd Credit\r\n").append("ADV ENGLISH II B 0.500\r\n")
                            .append("ADV ALGEBRA II A 0.500\r\n").append("CHEMISTRY I A 0.500\r\n")
                            .append("WORLD HIST ANCIENT A 0.500\r\n").append("SPANISH II A 0.500\r\n")
                            .append("DRIVERS EDUCATION A 0.500").toString());
            doc.add(paragraph);
            doc.close();
            channelSftp.cd("AutoIt scripts/Transcript");
            channelSftp.put(new FileInputStream(file), file.getName());
            new FileAccess().disconnect(channelSftp);
            return true;
        } catch (Exception e) {
            new FileAccess().disconnect(channelSftp);
            logger.info(e.getMessage());
            return false;
        }
    }

    public boolean createalphatranscriptpdf(String studentName, String studentDob, String docId, String transcriptFileName, boolean isValidFile){
        Document doc = new Document();
        if(!isValidFile){
            docId = "001".concat(docId);
        }
        try {
            String os = System.getProperty("os.name");
            if (os.equalsIgnoreCase("Mac OS X")) {
                PdfWriter.getInstance(doc, new FileOutputStream(
                        "/Users/admin/Documents/Selenium/AutoItscripts/sftp/" + transcriptFileName + ".pdf"));
            } else {
                PdfWriter.getInstance(doc,
                        new FileOutputStream("C:\\AutoIt scripts\\sftp\\" + transcriptFileName + ".pdf"));
            }
            doc.open();
            Paragraph pg = new Paragraph();
            Font font = FontFactory.getFont("Arial", 10);
            pg.setFont(font);
            pg.add("Transcript 1 \r\n" + "Alpha: " + docId + "\r\n" + studentName + "\r\n" + studentDob + "\r\nEND OF RECORD");
            doc.add(pg);
            doc.close();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }

    public boolean dfcuploadfile(String fn, String ln, String dob) {

        File newtxtfile = null;
        String studentname = fn + " " + ln;

        try {
            String os = System.getProperty("os.name");
            if (os.equalsIgnoreCase("Mac OS X")) {
                newtxtfile = new File("/Users/admin/Documents/Selenium/AutoItscripts/dfcupload/dfcupload.txt");
            } else {
                newtxtfile = new File("C:\\AutoIt scripts\\dfcupload\\dfcupload.txt");
            }
            FileWriter fw = new FileWriter(newtxtfile);
            fw.write(new StringBuilder().append("<META><sender-id>D01006</sender-id>\r\n").append("</META>\r\n")
                    .append("<VERSION ver=4.6 bN=62 os=2K/XP>\r\n").append("<PAGE dpi=300x300 mm=208x268>\r\n")
                    .append("<LOGFONT id=746>-42,0,0,0,400,0,0,0,0,7,0,0,34,Verdana\r\n").append("</LOGFONT>\r\n")
                    .append("<TEXT xy=941,115>zz RegressionTest\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1172,115>\r\n").append("</TEXT>\r\n").append("<TEXT xy=1297,115>\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=1050,169>S.\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1106,169>209th\r\n").append("</TEXT>\r\n").append("<TEXT xy=1239,169>Street\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=1378,169>West\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=986,221>HOLLAND\r\n").append("</TEXT>\r\n").append("<TEXT xy=1217,221>KS\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=1285,221>01234\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=953,274>PHONE:\r\n").append("</TEXT>\r\n").append("<TEXT xy=1130,273>(257)\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=1259,273>123-4567\r\n").append("</TEXT>\r\n")
                    .append("<LOGFONT id=747>-29,0,0,0,400,0,0,0,0,7,0,0,34,Verdana\r\n").append("</LOGFONT>\r\n")
                    .append("<TEXT xy=64,388>").append(fn).append("\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=176,388>Chris\r\n").append("</TEXT>\r\n").append("<TEXT xy=279,388>").append(ln)
                    .append("\r\n").append("</TEXT>\r\n").append("<TEXT xy=1226,388>GRD\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1297,388>LVL:\r\n").append("</TEXT>\r\n").append("<TEXT xy=1369,388>11\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=1635,388>PAR/GUAR:\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1805,388>Poul\r\n").append("</TEXT>\r\n").append("<TEXT xy=1863,388>").append(ln)
                    .append("\r\n").append("</TEXT>\r\n").append("<TEXT xy=64,425>10\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=108,424>Q\r\n").append("</TEXT>\r\n").append("<TEXT xy=144,423>RADAR\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=250,423>POINTE\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=366,423>CT\r\n").append("</TEXT>\r\n").append("<TEXT xy=833,425>ST\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=876,424>ID:\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=933,423>1222222221\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1226,425>GRAD\r\n").append("</TEXT>\r\n").append("<TEXT xy=1316,424>YR:\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=1373,423>2012\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1635,425>HM\r\n").append("</TEXT>\r\n").append("<TEXT xy=1688,424>PHONE:\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=1808,423>(257)\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1897,423>123-4567\r\n").append("</TEXT>\r\n").append("<TEXT xy=2179,425>PG:\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=2281,424>1\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=64,462>HOLLAND\r\n").append("</TEXT>\r\n").append("<TEXT xy=220,462>KS\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=266,462>62004\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=833,462>DOB\r\n").append("</TEXT>\r\n").append("<TEXT xy=920,462>:\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=941,462>").append(dob).append("\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1635,462>GENDER:\r\n").append("</TEXT>\r\n").append("<TEXT xy=1777,462>MALE\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=1993,462>PRINTED:\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=2141,462>01/18/2011\r\n").append("</TEXT>\r\n")
                    .append("<LOGFONT id=748>-29,0,0,0,700,0,0,0,0,7,0,0,34,Verdana Bold\r\n").append("</LOGFONT>\r\n")
                    .append("<TEXT xy=64,573>SUBJECT\r\n").append("</TEXT>\r\n").append("<TEXT xy=572,573>EARNED\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=770,573>TO\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=821,573>BE\r\n").append("</TEXT>\r\n").append("<TEXT xy=940,573>GR\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=993,573>09-09/2008-2009\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1302,573>GR\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1355,573>10-10/2009-2010\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1662,573>GR\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1715,573>11-11/2010-2011\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=2023,573>GR\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=2077,573>12-12/2011-2012\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=80,608>COURSE\r\n").append("</TEXT>\r\n").append("<TEXT xy=572,608>CREDITS\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=770,608>EARNED\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=253,608>SHORT\r\n").append("</TEXT>\r\n").append("<TEXT xy=369,608>DESC\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=959,608>S1\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1061,608>S2\r\n").append("</TEXT>\r\n").append("<TEXT xy=1318,608>S1\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=1420,608>S2\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1677,608>S1\r\n").append("</TEXT>\r\n").append("<TEXT xy=1779,608>S2\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=2036,608>S1\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=2138,608>S2\r\n").append("</TEXT>\r\n").append("<TEXT xy=64,659>01.\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=122,658>ENGLISH\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=272,656>9\r\n").append("</TEXT>\r\n").append("<LOGFONT id=747>\r\n")
                    .append("</LOGFONT>\r\n").append("<TEXT xy=80,710>1000\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=266,710>ENGLISH\r\n").append("</TEXT>\r\n").append("<TEXT xy=404,708>9\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=623,710>0.500\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=960,710>A-\r\n").append("</TEXT>\r\n").append("<TEXT xy=80,760>1001\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=266,760>ENGLISH\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=404,760>9\r\n").append("</TEXT>\r\n").append("<TEXT xy=623,760>0.500\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=1067,760>A-\r\n").append("</TEXT>\r\n")
                    .append("<LOGFONT id=748>\r\n").append("</LOGFONT>\r\n").append("<TEXT xy=64,827>02.\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=122,827>ENGLISH\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=272,827>10\r\n").append("</TEXT>\r\n").append("<LOGFONT id=747>\r\n")
                    .append("</LOGFONT>\r\n").append("<TEXT xy=80,877>1108\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=266,877>HNR\r\n").append("</TEXT>\r\n").append("<TEXT xy=335,877>ENGLISH\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=474,877>10\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=623,877>0.500\r\n").append("</TEXT>\r\n").append("<TEXT xy=1318,877>A-\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=80,928>1109\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=266,928>HNR\r\n").append("</TEXT>\r\n").append("<TEXT xy=335,928>ENGLISH\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=474,928>10\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=623,928>0.500\r\n").append("</TEXT>\r\n").append("<TEXT xy=1427,928>B\r\n")
                    .append("</TEXT>\r\n").append("<LOGFONT id=748>\r\n").append("</LOGFONT>\r\n")
                    .append("<TEXT xy=64,994>03.\r\n").append("</TEXT>\r\n").append("<TEXT xy=122,994>ENGLISH\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=272,994>11\r\n").append("</TEXT>\r\n")
                    .append("<LOGFONT id=747>\r\n").append("</LOGFONT>\r\n").append("<TEXT xy=80,1045>1208\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=266,1045>HNR\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=335,1045>ENGLISH\r\n").append("</TEXT>\r\n").append("<TEXT xy=474,1045>11\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=623,1045>0.500\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1676,1045>A-\r\n").append("</TEXT>\r\n").append("<TEXT xy=80,1096>1209\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=266,1096>HNR\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=344,1095>ENGLISH\r\n").append("</TEXT>\r\n").append("<TEXT xy=482,1094>11\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=832,1096>0.500\r\n").append("</TEXT>\r\n")
                    .append("<LOGFONT id=748>\r\n").append("</LOGFONT>\r\n").append("<TEXT xy=64,1162>05.\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=122,1162>COMMUNICATIONS\r\n").append("</TEXT>\r\n")
                    .append("<LOGFONT id=747>\r\n").append("</LOGFONT>\r\n").append("<TEXT xy=80,1213>1700\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=266,1213>COMMUNICATIONS\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=623,1213>0.500\r\n").append("</TEXT>\r\n").append("<TEXT xy=956,1213>B+\r\n")
                    .append("</TEXT>\r\n").append("<LOGFONT id=748>\r\n").append("</LOGFONT>\r\n")
                    .append("<TEXT xy=64,1279>06.\r\n").append("</TEXT>\r\n").append("<TEXT xy=122,1279>MATH\r\n")
                    .append("</TEXT>\r\n").append("<LOGFONT id=747>\r\n").append("</LOGFONT>\r\n")
                    .append("<TEXT xy=80,1330>2200\r\n").append("</TEXT>\r\n").append("<TEXT xy=266,1330>GEOMETRY\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=623,1330>0.500\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=956,1330>C+\r\n").append("</TEXT>\r\n").append("<TEXT xy=80,1381>2201\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=266,1381>GEOMETRY\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=623,1381>0.500\r\n").append("</TEXT>\r\n").append("<TEXT xy=1067,1381>B-\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=80,1431>2102\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=266,1431>ALGEBRA\r\n").append("</TEXT>\r\n").append("<TEXT xy=406,1431>II\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=623,1431>0.500\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1313,1431>C+\r\n").append("</TEXT>\r\n").append("<TEXT xy=80,1482>2103\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=266,1482>ALGEBRA\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=406,1482>II\r\n").append("</TEXT>\r\n").append("<TEXT xy=623,1482>0.500\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=1427,1482>C\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=80,1533>2300\r\n").append("</TEXT>\r\n").append("<TEXT xy=266,1533>PRE\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=328,1532>CALCULUS\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=623,1533>0.500\r\n").append("</TEXT>\r\n").append("<TEXT xy=1676,1533>B-\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=80,1584>2301\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=266,1584>PRE\r\n").append("</TEXT>\r\n").append("<TEXT xy=328,1583>CALCULUS\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=832,1584>0.500\r\n").append("</TEXT>\r\n")
                    .append("<LOGFONT id=748>\r\n").append("</LOGFONT>\r\n").append("<TEXT xy=64,1650>07.\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=122,1649>SCIENCE\r\n").append("</TEXT>\r\n")
                    .append("<LOGFONT id=747>\r\n").append("</LOGFONT>\r\n").append("<TEXT xy=80,1701>4000\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=266,1701>PHYSICAL\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=416,1700>SCIENCE\r\n").append("</TEXT>\r\n").append("<TEXT xy=623,1701>0.500\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=960,1701>A-\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=80,1752>4001\r\n").append("</TEXT>\r\n").append("<TEXT xy=266,1752>PHYSICAL\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=416,1752>SCIENCE\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=623,1752>0.500\r\n").append("</TEXT>\r\n").append("<TEXT xy=1067,1752>A-\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=80,1802>4006\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=266,1802>BIOLOGY\r\n").append("</TEXT>\r\n").append("<TEXT xy=623,1802>0.500\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=1321,1802>B\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=80,1853>4007\r\n").append("</TEXT>\r\n").append("<TEXT xy=266,1853>BIOLOGY\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=623,1853>0.500\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1427,1853>A\r\n").append("</TEXT>\r\n").append("<TEXT xy=80,1904>4300\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=266,1904>CHEMISTRY\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=442,1903>I\r\n").append("</TEXT>\r\n").append("<TEXT xy=623,1904>0.500\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=1678,1904>B\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=80,1955>4301\r\n").append("</TEXT>\r\n").append("<TEXT xy=266,1955>CHEMISTRY\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=442,1954>I\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=832,1955>0.500\r\n").append("</TEXT>\r\n").append("<LOGFONT id=748>\r\n")
                    .append("</LOGFONT>\r\n").append("<TEXT xy=64,2021>08.\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=122,2020>U.S.\r\n").append("</TEXT>\r\n").append("<TEXT xy=193,2018>HISTORY\r\n")
                    .append("</TEXT>\r\n").append("<LOGFONT id=747>\r\n").append("</LOGFONT>\r\n")
                    .append("<TEXT xy=80,2072>3104\r\n").append("</TEXT>\r\n").append("<TEXT xy=266,2072>AP\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=309,2070>U.S.\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=378,2070>HISTORY\r\n").append("</TEXT>\r\n").append("<TEXT xy=623,2072>0.500\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=1678,2072>B\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=80,2122>3105\r\n").append("</TEXT>\r\n").append("<TEXT xy=266,2122>AP\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=309,2122>U.S.\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=378,2122>HISTORY\r\n").append("</TEXT>\r\n").append("<TEXT xy=832,2122>0.500\r\n")
                    .append("</TEXT>\r\n").append("<LOGFONT id=748>\r\n").append("</LOGFONT>\r\n")
                    .append("<TEXT xy=64,2189>09.\r\n").append("</TEXT>\r\n").append("<TEXT xy=122,2189>U.S.\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=193,2189>HISTORY\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=345,2189>10\r\n").append("</TEXT>\r\n").append("<LOGFONT id=747>\r\n")
                    .append("</LOGFONT>\r\n").append("<TEXT xy=80,2239>3106\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=266,2239>U.S.\r\n").append("</TEXT>\r\n").append("<TEXT xy=334,2239>HISTORY\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=470,2239>10\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=623,2239>0.500\r\n").append("</TEXT>\r\n").append("<TEXT xy=1318,2239>A-\r\n")
                    .append("</TEXT>\r\n").append("<LOGFONT id=748>\r\n").append("</LOGFONT>\r\n")
                    .append("<TEXT xy=64,2306>11.\r\n").append("</TEXT>\r\n").append("<TEXT xy=122,2306>WORLD\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=249,2306>HISTORY\r\n").append("</TEXT>\r\n")
                    .append("<LOGFONT id=747>\r\n").append("</LOGFONT>\r\n").append("<TEXT xy=80,2356>3107\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=266,2356>WORLD\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=382,2356>HISTORY\r\n").append("</TEXT>\r\n").append("<TEXT xy=517,2356>1\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=623,2356>0.500\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1427,2356>B\r\n").append("</TEXT>\r\n").append("<LOGFONT id=748>\r\n")
                    .append("</LOGFONT>\r\n").append("<TEXT xy=64,2422>12.\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=122,2422>SOCIAL\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=249,2422>STUDIES\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=397,2422>ELECTIVE\r\n").append("</TEXT>\r\n").append("<LOGFONT id=747>\r\n")
                    .append("</LOGFONT>\r\n").append("<TEXT xy=80,2473>3300\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=266,2473>PSYCHOLOGY\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=623,2473>0.500\r\n").append("</TEXT>\r\n").append("<TEXT xy=960,2473>A-\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=80,2524>3301\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=266,2524>SOCIOLOGY\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=623,2524>0.500\r\n").append("</TEXT>\r\n").append("<TEXT xy=1069,2524>B\r\n")
                    .append("</TEXT>\r\n").append("<LOGFONT id=748>\r\n").append("</LOGFONT>\r\n")
                    .append("<TEXT xy=64,2590>13.\r\n").append("</TEXT>\r\n").append("<TEXT xy=122,2590>HEALTH\r\n")
                    .append("</TEXT>\r\n").append("<LOGFONT id=747>\r\n").append("</LOGFONT>\r\n")
                    .append("<TEXT xy=80,2641>9599\r\n").append("</TEXT>\r\n").append("<TEXT xy=266,2641>HEALTH\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=623,2641>0.500\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1318,2641>A-\r\n").append("</TEXT>\r\n").append("<LOGFONT id=748>\r\n")
                    .append("</LOGFONT>\r\n").append("<TEXT xy=64,2707>14.\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=122,2707>PHYSICAL\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=289,2707>EDUCATION\r\n").append("</TEXT>\r\n").append("<LOGFONT id=747>\r\n")
                    .append("</LOGFONT>\r\n").append("<TEXT xy=80,2758>9606\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=266,2758>FR\r\n").append("</TEXT>\r\n").append("<TEXT xy=309,2757>GIRLS\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=406,2756>PHY\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=470,2756>ED\r\n").append("</TEXT>\r\n").append("<TEXT xy=623,2758>0.500\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=963,2758>A\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=80,2809>9607\r\n").append("</TEXT>\r\n").append("<TEXT xy=266,2809>FR\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=309,2808>GIRLS\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=406,2808>PHY\r\n").append("</TEXT>\r\n").append("<TEXT xy=470,2808>ED\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=623,2809>0.500\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=1067,2809>B-\r\n").append("</TEXT>\r\n").append("<LOGFONT id=748>\r\n")
                    .append("</LOGFONT>\r\n").append("<TEXT xy=64,2875>15.\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=122,2874>FINE\r\n").append("</TEXT>\r\n").append("<TEXT xy=206,2873>ARTS\r\n")
                    .append("</TEXT>\r\n").append("<LOGFONT id=747>\r\n").append("</LOGFONT>\r\n")
                    .append("<TEXT xy=80,2926>8500\r\n").append("</TEXT>\r\n").append("<TEXT xy=266,2926>INTRO\r\n")
                    .append("</TEXT>\r\n").append("<TEXT xy=366,2925>TO\r\n").append("</TEXT>\r\n")
                    .append("<TEXT xy=413,2925>ART\r\n").append("</TEXT>\r\n").append("<TEXT xy=832,2926>0.500\r\n")
                    .append("</TEXT>\r\n").append("<FF>\r\n").append("<FF>\r\n").append("").toString());
            fw.close();
            return true;

        } catch (Exception e) {

            logger.info(e.getMessage());
            return false;
        }
    }

    public boolean createPdfForNewRegisteredStudent(String studentEmail) {
        try {
            Document doc = new Document();
            List<Object> studentDetails = new DatabaseConnection().getstudentdetails(studentEmail);
            String studentFirstName = studentDetails.get(0).toString();
            String studentLastName = studentDetails.get(1).toString();
            String dob = studentDetails.get(3).toString();

            String os = System.getProperty("os.name");
            if (os.equalsIgnoreCase("Mac OS X")) {
                PdfWriter.getInstance(doc,
                        new FileOutputStream("/Users/admin/Documents/Selenium/AutoItscripts/Transcript/TS.pdf"));
            } else {
                PdfWriter.getInstance(doc, new FileOutputStream("C:\\AutoIt scripts\\Transcript\\TS.pdf"));
            }
            doc.open();
            Paragraph pg = new Paragraph();
            Font font = FontFactory.getFont("Arial", 10);
            pg.setFont(font);
            pg.add(new StringBuilder().append("Student: ").append(studentFirstName).append(" ").append(studentLastName)
                    .append("\r\nDOB: ").append(dob).append("\r\nID: \r\n")
                    .append("Address: 123 Main Street Guardian:\r\n").append("Tempe, AZ 85221\r\n")
                    .append("Graduation/Leave Year: 2017\r\n").append("Principal: John Miller\r\n")
                    .append("Completed US Constitution State Constitution Drivers Ed Consumer Ed PSAE\r\n")
                    .append("Cred Fall 2010-2011\r\n").append("Grade: 10-Sandwich High School\r\n")
                    .append("Course Grd Credit\r\n").append("ADV ENGLISH II B 0.500\r\n")
                    .append("ADV ALGEBRA II A 0.500\r\n").append("CHEMISTRY I A 0.500\r\n")
                    .append("WORLD HIST ANCIENT A 0.500\r\n").append("SPANISH II A 0.500\r\n")
                    .append("DRIVERS EDUCATION A 0.500").toString());
            doc.add(pg);
            doc.close();
            return true;

        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }

    public boolean createTxtTranscript(String studentFirstName, String studentLastName, String studentDob, String remoteIp) {
        try {
            File newTxtFile = new File("newordersftptranscript.txt");
            setTxtTranscriptFileName("newordersftptranscript.txt");
            FileWriter fw = new FileWriter(newTxtFile);
            fw.write(new StringBuilder().append("Name: ").append(studentFirstName).append(" ").append(studentLastName)
                    .append("                           Student ID: 5489495\r\n")
                    .append("      123 N Somehwere Ave                    Soc Sec #: xxx - xx - 1234\r\n")
                    .append("      Nowhere, AZ  12345-6789                DOB: ").append(studentDob).append("\r\n")
                    .append("                                               Program: Undergraduate\n").append("\r\n")
                    .append("\r\n").append("\r\n").append("\r\n").append("\r\n").append("\r\n")
                    .append("-------------------- Summer 2007 --------------------  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .\r\n")
                    .append("NUR310   Expl Prof Nurs Opport          4.0  A   16.0\r\n")
                    .append("             1/15/02 - 7/28/02                         .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .\r\n")
                    .append("NUR410   Research in Nursing            3.0  W    0.0\r\n")
                    .append("             8/12/02 - 9/17/02                         .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .\r\n")
                    .append("      attempt   earn    pass  gpahrs    gpa    points\r\n")
                    .append("ses     7.00    4.00    0.00    4.00   4.000    16.00  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .\r\n")
                    .append("cum     7.00    4.00    0.00    4.00   4.000    16.00\r\n")
                    .append("-----------------------------------------------------  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .\r\n")
                    .append("                Printed on 09/01/2015\r\n")
                    .append("============ NO ENTRIES BELOW THIS LINE =============  .  .   .  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .\r\n")
                    .append("\r\n").append("\r\n")
                    .append("In accordance with the Family Educational Rights        This official transcript is printed on security\r\n")
                    .append("and Privacy Act of 1974, access to this record by       paper and bears a blue signature stamp embosse\r\n")
                    .append("any party without written consent of the student        with the college seal.  The word COPY appears\r\n")
                    .append("is prohibited.  An explanation of the transcript        when photocopied.").toString());
            fw.close();
            FileAccess fileAccess = new FileAccess();
            ChannelSftp channelSftp= fileAccess.getChannelSftp(remoteIp);
            channelSftp.cd("AutoIt scripts/sftp/transcriptupload");
            channelSftp.put(new FileInputStream(newTxtFile), newTxtFile.getName());
            fileAccess.disconnect(channelSftp);
            return true;

        } catch (Exception e) {

            logger.info(e.getMessage());
            return false;
        }
    }

}
