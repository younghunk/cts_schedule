package com.schedule.genimg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Setter
@Slf4j
public class ImageGenerator {
    private String REG_DATE;
    private String Hours;
    private String Min;
    private String Temp2;
    private String Temp3;
    private String COM_NM;
    private String PD_BARCODE;
    private String PD_BARCODE_L;
    private String PD_NM;
    private String PD_DELI_GUBUN;
    private int PD_CNT;
    private String PD_PACK;
    private String PD_DOCK1;
    private String BR_END_NM;
    private String BR_END_TEL;
    private String BL;
    private String SEND_NM;
    private String RECV_TEL1;
    private String RECV_NM;
    private String RECV_ZIPCODE;
    private String RECV_ADDR1;
    private String RECV_ADDR2;
    private String PD_MEMO;
    private String PD_DOCK2;
    private String PD_TERMINAL1;

    public byte[] generateImage() throws IOException {
        BufferedImage image = new BufferedImage(760, 400, BufferedImage.TYPE_BYTE_INDEXED);

        Graphics2D g2d = image.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);                       // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);                        // Set rendering quality
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);             // Set image interpolation

        try {
            Image img = ImageIO.read(new ClassPathResource("static/images/backGround.jpg").getInputStream());
            Image img2 = ImageIO.read(new ClassPathResource("static/images/logo.jpg").getInputStream());
            g2d.drawImage(img, 0, 0, null);
            /*g2d.drawImage(img2, 250, 355, 60, 20, null);*/
            g2d.drawImage(img2, 250, 358, 60, 20, null);
            allTextDesign(g2d);
            g2d.dispose();

        } catch (IOException e) {
            log.debug(e.getCause().toString());
            System.out.println("Error creating the image.");
        }

//        File outputImage = new File("D:\\Invoice\\customer_info_" + new Random().nextInt(2000000) + ".png");
//        try {
//            ImageIO.write(image, "png", outputImage);
//            System.out.println("Image created successfully.");
//        } catch (IOException e) {
//            log.debug(e.getCause().toString());
//            System.out.println("Error creating the image.");
//        }

        ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOutput);
        return byteArrayOutput.toByteArray();
    }

    private void allTextDesign(Graphics g) throws IOException {
        Font koreanBold30 = new Font("Malgun Gothic", Font.BOLD, 30);
        Font koreanBold35 = new Font("Malgun Gothic", Font.BOLD, 35);
        Font koreanBold10 = new Font("맑은 고딕 (Body Asian)", Font.BOLD, 10);
        Font koreanPlain10 = new Font("맑은 고딕 (Body Asian)", Font.PLAIN, 10);
        Font koreanPlain15 = new Font("맑은 고딕 (Body Asian)", Font.PLAIN, 15);
        Font koreanBold08 = new Font("맑은 고딕 (Body Asian)", Font.BOLD, 8);
        Font koreanBold11 = new Font("맑은 고딕 (Body Asian)", Font.BOLD, 11);
        Font koreanBold17 = new Font("맑은 고딕 (Body Asian)", Font.BOLD, 17);
        Font koreanBold15 = new Font("맑은 고딕 (Body Asian)", Font.BOLD, 15);
        Font koreanBold20 = new Font("맑은 고딕 (Body Asian)", Font.BOLD, 20);

        Font englishBold20 = new Font("Arial", Font.BOLD, 20);
        Font englishPlain14 = new Font("Arial", Font.PLAIN, 14);

        g.setColor(Color.BLACK);
        g.setFont(koreanBold10);
        g.drawString("발송영업소", 15, 25);
        g.drawString("도작영업소", 350, 25);

        if (REG_DATE != null && !REG_DATE.isEmpty()) {
            g.setFont(englishBold20);
            g.drawString(REG_DATE + "(" + Hours + ". " + Min + ". " + Temp2 + ". " + Temp3 + ")", 18, 50);
        }

        g.setFont(koreanBold17);
        g.drawString("인천허브넷 황대일,김종철", 18, 75);

        g.setFont(koreanBold17);
        g.drawString("허브넷로지스틱스", 18, 100);

        g.setFont(koreanBold17);
        g.drawString("070-4210-2657", 18, 135);

        g.setFont(koreanPlain10);
        g.drawString("(22345) 인천 중구 내항로 67 (항동7가)", 18, 150);

        g.setFont(koreanPlain10);
        g.drawString("착택은 확인배송요망 ! 선배달후 못받은 착불비 책임지지않습니다", 18, 170);

        if (PD_BARCODE != null && !PD_BARCODE.isEmpty()) {
            g.setFont(englishBold20);
            g.drawString("S/N  " + PD_BARCODE, 18, 195);
        }
        g.setFont(englishBold20);
        g.drawString("___________________________", 18, 200);

        if (PD_NM != null && !PD_NM.isEmpty()) {
            g.setFont(englishPlain14);
            g.drawString(PD_NM, 18, 220);
        }

        if (PD_DELI_GUBUN != null && !PD_DELI_GUBUN.isEmpty()) {
            g.setFont(koreanBold17);
            g.drawString(PD_DELI_GUBUN + " - " + PD_CNT + " / " + PD_PACK, 18, 245);
        }

        g.setFont(englishBold20);
        g.drawString("___________________________", 18, 250);

        if (PD_DOCK1 != null && !PD_DOCK1.isEmpty()) {
            g.setFont(koreanBold35);
            g.drawString(PD_DOCK1, 18, 290);
        }

        if (PD_BARCODE != null && !PD_BARCODE.isEmpty()) {
            g.setFont(koreanBold10);
            BufferedImage rightBarcode = generateBarcode(PD_BARCODE, 260, 50);
            g.drawImage(resizeBarcode(rightBarcode, 300), 25, 305, null);
        }
        if (PD_BARCODE != null && !PD_BARCODE.isEmpty()) {
            g.setFont(koreanBold11);
            g.drawString(PD_BARCODE, 130, 373);
        }


        //Right Side Start
        if (BR_END_NM != null && !BR_END_NM.isEmpty()) {
            g.setFont(koreanBold17);
            g.drawString(BR_END_NM, 350, 50);
        }
        /*g.setFont(koreanBold17);
        g.drawString("김용기", 600, 50);*/

        if (BR_END_TEL != null && !BR_END_TEL.isEmpty()) {
            g.setFont(koreanPlain10);
            g.drawString(BR_END_TEL, 350, 65);
        }
        if (BL != null && !BL.isEmpty()) {
            g.setFont(koreanPlain15);
            g.drawString(BL, 425, 75);
        }
        if (SEND_NM != null && !SEND_NM.isEmpty()) {
            g.setFont(koreanPlain15);
            g.drawString(SEND_NM, 580, 75);
        }
        g.setFont(koreanBold10);
        g.drawString("받는고객명", 350, 85);

        if (RECV_NM != null && !RECV_NM.isEmpty()) {
            g.setFont(koreanBold20);
            g.drawString(RECV_NM, 350, 110);
        }

        if (RECV_TEL1 != null && !RECV_TEL1.isEmpty()) {
            g.setFont(koreanBold20);
            g.drawString("(" + RECV_TEL1 + ")", 350, 135);
        }
        g.setFont(koreanBold11);
        g.drawString("받는고객주소", 350, 160);

        if (RECV_ADDR1 != null && !RECV_ADDR1.isEmpty()) {
            g.setFont(koreanBold17);
            g.drawString("(" + RECV_ZIPCODE + ") " + RECV_ADDR1, 350, 182);
        }
        if (RECV_ADDR2 != null && !RECV_ADDR2.isEmpty()) {
            g.setFont(koreanBold17);
            g.drawString(RECV_ADDR2, 350, 202);
        }
        g.setFont(englishBold20);
        g.drawString("___________________________", 350, 210);

        if (PD_MEMO != null && !PD_MEMO.isEmpty()) {
            g.setFont(koreanBold17);
            if (PD_MEMO.length() > 20) {
                String memo1 = PD_MEMO.substring(0, 20);
                String memo2 = PD_MEMO.substring(20);
                g.drawString(memo1, 350, 230);
                g.drawString(memo2, 350, 250);
            } else {
                g.drawString(PD_MEMO, 350, 230);
            }
        }

        g.setFont(englishBold20);
        g.drawString("___________________________", 350, 250);

        if (PD_DOCK2 != null && !PD_DOCK2.isEmpty()) {
            g.setFont(koreanBold35);
            g.drawString(PD_DOCK2, 350, 290);
        }

        g.setFont(koreanBold08);
        g.drawString("고객센터 : 1899-5368", 340, 330);

        if (PD_BARCODE != null && !PD_BARCODE.isEmpty()) {
            BufferedImage leftBarcode = generateBarcode(PD_BARCODE, 400, 40);
            g.drawImage(resizeBarcode(leftBarcode, 240), 420, 295, null);
        }
        // Right side Boxes
        g.setColor(Color.BLACK);
        String[] terminals = {"고촌", "군포", "화성", "칠곡", "양산", "장수", "인천"};
        for (int i = 0; i < terminals.length; i++) {
            if (PD_TERMINAL1 != null && !PD_TERMINAL1.isEmpty()) {
                if (!PD_TERMINAL1.equals(terminals[i])) {
                    g.fillRect(682, 28 + (i * 48), 56, 42);
                }
            }
        }
        /*for (String terminal : terminals) {
            if (PD_TERMINAL1 != null && !PD_TERMINAL1.isEmpty()) {
                if (!PD_TERMINAL1.equals(terminal)){
                    g.fillRect(682, 28 + (terminal.indexOf(terminal) * 53), 56, 42);
                }
            }
        }*/
    }


    private BufferedImage generateBarcode(String data, int width, int height) {
        try {
            Code128Writer barcodeWriter = new Code128Writer();
            BitMatrix bitMatrix = barcodeWriter.encode(data, BarcodeFormat.CODE_128, width, height);
            // Convert the BitMatrix to a BufferedImage
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            int minX = bufferedImage.getWidth();
            int minY = bufferedImage.getHeight();
            int maxX = -1;
            int maxY = -1;
            // Find the boundaries of the non-white pixels
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    if (bufferedImage.getRGB(x, y) != Color.WHITE.getRGB()) {
                        minX = Math.min(minX, x);
                        minY = Math.min(minY, y);
                        maxX = Math.max(maxX, x);
                        maxY = Math.max(maxY, y);
                    }
                }
            }
            bufferedImage = bufferedImage.getSubimage(minX, minY, maxX - minX + 1, maxY - minY + 1);
            return bufferedImage;

        } catch (Exception e) {
            log.debug(e.getCause().toString());
        }
        return null;
    }

    private BufferedImage resizeBarcode(BufferedImage image, int width) {
        BufferedImage resizedImage = new BufferedImage(width, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, 50, null);
        g2d.dispose();
        return resizedImage;
    }

}

//    public ImageGenerator() {
//    }
//
//    public Blob startGeneratingImage() throws IOException, SQLException {
//        JFrame frame;
//        frame = new JFrame();
//        frame.setTitle("My JFrame");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Specify the path to the image file
//        String imageBackGround = "src/main/resources/static/images/backGround.jpg";
//        String imageLogo = "src/main/resources/static/images/logo.jpg";
//
////         Create an ImagePanel with the specified image path
//        ImageGenerator panelDetails = new ImageGenerator(imageBackGround, imageLogo);
//        panelDetails.setPreferredSize(new Dimension(760, 400));
//        panelDetails.setBackground(Color.WHITE);
//
//        frame.setContentPane(panelDetails);
//
//        frame.pack();
////        frame.setVisible(true);
//        BufferedImage image = new BufferedImage(760, 400, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2d = image.createGraphics();
//        panelDetails.paint(g2d);
//        g2d.dispose();
//
//        //Remove this later
//        File output = new File("D:\\Scripts\\customer_info_" + new Random().nextInt(2000000) + ".png");
//        ImageIO.write(image, "png", output);
//
//        ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
//        ImageIO.write(image, "jpg", byteArrayOutput);
//        byte[] imageData = byteArrayOutput.toByteArray();
//        return new SerialBlob(imageData);
//    }
//
//    private void allTextDesign(Graphics g) throws IOException {
//        Font koreanBold30 = new Font("Malgun Gothic", Font.BOLD, 30);
//        Font koreanBold10 = new Font("맑은 고딕 (Body Asian)", Font.BOLD, 10);
//        Font koreanPlain10 = new Font("맑은 고딕 (Body Asian)", Font.PLAIN, 10);
//        Font koreanBold11 = new Font("맑은 고딕 (Body Asian)", Font.BOLD, 11);
//        Font koreanBold17 = new Font("맑은 고딕 (Body Asian)", Font.BOLD, 17);
//
//        Font englishBold20 = new Font("Arial", Font.BOLD, 20);
//        Font englishPlain14 = new Font("Arial", Font.PLAIN, 14);
//
//        g.setColor(Color.BLACK);
//        g.setFont(koreanBold10);
//        g.drawString("발숭영업소", 15, 25);
//        g.drawString("도작영업소", 350, 25);
//
//        if (REG_DATE != null) {
//            g.setFont(englishBold20);
//            g.drawString(REG_DATE + "(" + Hours + ". " + Min + ". " + Temp2 + ". " + Temp3 + ")", 18, 50);
//        }
//
//        g.setFont(koreanBold17);
//        g.drawString("평택청룡62인천중구금왕", 18, 75);
//
//        g.setFont(koreanBold17);
//        g.drawString("퀵스타", 18, 100);
//
//        if (COM_NM != null) {
//            g.setFont(koreanBold17);
//            g.drawString(COM_NM, 18, 135);
//        }
//        g.setFont(koreanPlain10);
//        g.drawString("(22345) 인천 중구 내항로 67 (항동7가)", 18, 150);
//
//        g.setFont(koreanBold10);
//        g.drawString("착택은 확인배송요망 ! 선배달후 못받은 착불비 책임지지않습니다", 18, 170);
//
//        if (PD_BARCODE != null) {
//            g.setFont(englishBold20);
//            g.drawString("S/N  " + PD_BARCODE, 18, 200);
//        }
//        g.setFont(englishBold20);
//        g.drawString("___________________________", 18, 205);
//
//        if (PD_NM != null) {
//            g.setFont(englishPlain14);
//            g.drawString(PD_NM, 18, 225);
//        }
//
//        if (PD_DELI_GUBUN != null) {
//            g.setFont(koreanBold17);
//            g.drawString(PD_DELI_GUBUN + " - " + PD_CNT + " / " + PD_PACK, 18, 255);
//        }
//
//        g.setFont(englishBold20);
//        g.drawString("___________________________", 18, 260);
//
//        if (PD_DOCK1 != null) {
//            g.setFont(koreanBold30);
//            g.drawString(PD_DOCK1, 18, 300);
//        }
//        if (PD_BARCODE != null) {
//            g.setFont(koreanBold10);
//            BufferedImage rightBarcode = generateBarcode(PD_BARCODE, 260, 50);
//            g.drawImage(resizeBarcode(rightBarcode, 200, 40), 50, 315, this);
//        }
//        if (PD_BARCODE != null) {
//            g.setFont(koreanBold11);
//            g.drawString(PD_BARCODE, 130, 375);
//        }
//        //Right Side Start
//        if (BR_END_NM != null) {
//            g.setFont(koreanBold17);
//            g.drawString(BR_END_NM, 350, 50);
//        }
//        g.setFont(koreanBold17);
//        g.drawString("김용기", 600, 50);
//
//        if (BR_END_TEL != null) {
//            g.setFont(koreanPlain10);
//            g.drawString(BR_END_TEL, 350, 65);
//        }
//        if (BL != null) {
//            g.setFont(koreanPlain10);
//            g.drawString(BL, 500, 75);
//        }
//        if (SEND_NM != null) {
//            g.setFont(koreanPlain10);
//            g.drawString(SEND_NM, 600, 75);
//        }
//        g.setFont(koreanBold10);
//        g.drawString("받는고객명", 350, 85);
//
//        if (RECV_NM != null) {
//            g.setFont(koreanBold17);
//            g.drawString(RECV_NM, 350, 110);
//        }
//
//        if (RECV_TEL1 != null) {
//            g.setFont(koreanBold17);
//            g.drawString("(" + RECV_TEL1 + ")", 350, 135);
//        }
//        g.setFont(koreanBold11);
//        g.drawString("받는고객주소", 350, 160);
//
//        if (RECV_ADDR1 != null) {
//            g.setFont(koreanBold17);
//            g.drawString("(" + RECV_ZIPCODE + ") " + RECV_ADDR1, 350, 180);
//        }
//        if (RECV_ADDR2 != null) {
//            g.setFont(koreanBold17);
//            g.drawString(RECV_ADDR2, 350, 200);
//        }
//        g.setFont(englishBold20);
//        g.drawString("___________________________", 350, 210);
//
//        if (PD_MEMO != null) {
//            g.setFont(koreanBold17);
//            g.drawString(PD_MEMO, 350, 220);
//        }
//
//        g.setFont(englishBold20);
//        g.drawString("___________________________", 350, 260);
//
//        if (PD_DOCK2 != null) {
//            g.setFont(koreanBold30);
//            g.drawString(PD_DOCK2, 350, 295);
//        }
//
//        g.setFont(koreanBold11);
//        g.drawString("고객센터 : 1899-5368", 350, 330);
//
//        if (PD_BARCODE != null) {
//            BufferedImage leftBarcode = generateBarcode(PD_BARCODE, 400, 40);
//            g.drawImage(resizeBarcode(leftBarcode, 170, 40), 480, 300, this);
//        }
//        // Right side Boxes
//        g.setColor(Color.BLACK);
//        String[] terminals = {"고촌", "군포", "화성", "칠곡", "양산", "장수", "인천"};
//        for (String terminal : terminals) {
//            if (!PD_TERMINAL1.trim().equals(terminal))
//                g.fillRect(682, 28 + (terminal.indexOf(terminal) * 53), 56, 42);
//        }
//
////        if (!PD_TERMINAL1.trim().equals("고촌"))
////            g.fillRect(682, 28, 56, 42);
////        if (!PD_TERMINAL1.trim().equals("군포"))
////            g.fillRect(682, 78, 56, 42);
////        if (!PD_TERMINAL1.trim().equals("화성"))
////            g.fillRect(682, 125, 56, 42);
////        if (!PD_TERMINAL1.trim().equals("칠곡"))
////            g.fillRect(682, 175, 56, 42);
////        if (!PD_TERMINAL1.trim().equals("양산"))
////            g.fillRect(682, 223, 56, 42);
////        if (!PD_TERMINAL1.trim().equals("장수"))
////            g.fillRect(682, 273, 56, 42);
////        if (!PD_TERMINAL1.trim().equals("인천"))
////            g.fillRect(682, 325, 56, 42);
//
//    }
//
//    public ImageGenerator(String imageBackGround, String imageLogo) {
//        try {
//            BackGround = ImageIO.read(new File(imageBackGround));
//            Logo = resizeImage(imageLogo);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        if (BackGround != null && Logo != null) {
//            try {
//                g.drawImage(BackGround, 0, 0, this);
//                int xOffset = 250;
//                int yOffset = 350;
//                g.drawImage(Logo, xOffset, yOffset, this);
//                allTextDesign(g);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//
//    private BufferedImage generateBarcode(String data, int width, int height) {
//        try {
//            Code128Writer barcodeWriter = new Code128Writer();
//            BitMatrix bitMatrix = barcodeWriter.encode(data, BarcodeFormat.CODE_128, width, height);
//            // Convert the BitMatrix to a BufferedImage
//            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
//
//            int minX = bufferedImage.getWidth();
//            int minY = bufferedImage.getHeight();
//            int maxX = -1;
//            int maxY = -1;
//            // Find the boundaries of the non-white pixels
//            for (int x = 0; x < bufferedImage.getWidth(); x++) {
//                for (int y = 0; y < bufferedImage.getHeight(); y++) {
//                    if (bufferedImage.getRGB(x, y) != Color.WHITE.getRGB()) {
//                        minX = Math.min(minX, x);
//                        minY = Math.min(minY, y);
//                        maxX = Math.max(maxX, x);
//                        maxY = Math.max(maxY, y);
//                    }
//                }
//            }
//            bufferedImage = bufferedImage.getSubimage(minX, minY, maxX - minX + 1, maxY - minY + 1);
//            return bufferedImage;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private BufferedImage resizeImage(String imagePath) throws IOException {
//        BufferedImage originalImage = ImageIO.read(new File(imagePath));
//        BufferedImage resizedImage = new BufferedImage(60, 20, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2d = resizedImage.createGraphics();
//        g2d.drawImage(originalImage, 0, 0, 60, 20, null);
//        g2d.dispose();
//        return resizedImage;
//    }
//
//    private BufferedImage resizeBarcode(BufferedImage image, int width, int height) {
//        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2d = resizedImage.createGraphics();
//        g2d.drawImage(image, 0, 0, width, height, null);
//        g2d.dispose();
//        return resizedImage;
//    }
//}
