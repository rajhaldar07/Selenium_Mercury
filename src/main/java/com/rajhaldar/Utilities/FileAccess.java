package com.parchment.Util;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.InputStream;
import java.io.OutputStream;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileAccess {

    private static final Logger logger = LoggerFactory.getLogger(FileAccess.class);

/*    public static void main(String[] args) {
		*//*storedatassh(1, 1, "Testnewssh");
		System.out.println(getdatassh(1, 1));*//*
        new FileAccess().executeCommand("172.16.47.68", "\"c:\\AutoIt scripts\\Uploadtranscript.exe\"");
//		FileAccess.executeCommand("172.16.47.65", "taskkill /F /IM chrome.exe");
        *//*storedata(1, 1, "Testnetworkaccess");
        System.out.println(getdata(1, 1));*//*
    }*/

    public InputStream getInputStream(String remoteIp, String filePath) {
        try {
            return new SmbFile("smb://".concat(remoteIp).concat("/").concat(filePath), getAuthentication())
                    .getInputStream();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }

    }

    public OutputStream getOutputStream(String remoteIp, String filePath) {
        try {
            return new SmbFile("smb://".concat(remoteIp).concat("/").concat(filePath), getAuthentication())
                    .getOutputStream();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }

    }

    public SmbFile getFile(String remoteIp, String filePath) {
        try {
            return new SmbFile("smb://".concat(remoteIp).concat("/").concat(filePath), getAuthentication());
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isFileExists(String remoteIp, String filePath) {
        try {
            return new SmbFile("smb://".concat(remoteIp).concat("/").concat(filePath), getAuthentication()).exists();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }

    }

    public void createFile(String remoteIp, String filePath) {
        try {
            new SmbFile("smb://".concat(remoteIp).concat("/").concat(filePath), getAuthentication()).createNewFile();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

    }

    public void deleteFile(String remoteIp, String filePath) {
        try {
            new SmbFile("smb://".concat(remoteIp).concat("/").concat(filePath), getAuthentication()).delete();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

    }

    public NtlmPasswordAuthentication getAuthentication() {
        return new NtlmPasswordAuthentication("", "testaccount", "Test@account1234");
    }

    public ChannelSftp getChannelSftp(String remoteIp) {
        try {
            JSch jSch = new JSch();
            Session session = jSch.getSession("auto", remoteIp, 22);
            session.setPassword("auto");
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            return channelSftp;
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return null;
    }

    public void executeCommand(String remoteIp, String command) {
        JSch jSch = new JSch();
        Session session = null;
        ChannelExec channelExec = null;
        try {
            session = jSch.getSession("auto", remoteIp, 22);
            session.setPassword("auto");
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand("cmd /c ".concat(command));
            channelExec.connect();
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (channelExec != null) {
                channelExec.disconnect();
                session.disconnect();
            }
        }
    }

    public void disconnect(ChannelSftp channelSftp) {
        try {
            if (channelSftp != null) {
                Session session = channelSftp.getSession();
                channelSftp.disconnect();
                session.disconnect();
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}
