package cn.edu.xhu.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import cn.edu.xhu.consts.CommonConsts;
import cn.edu.xhu.consts.QRCodeConsts;
import cn.edu.xhu.exception.QRCodeCreateException;

/**
 * 根据内容与二维码产生图片
 * 
 * @author peng
 *
 */
public class QRCodeUtil {
	public static void createQRCode(String content, String path) throws QRCodeCreateException {
		HashMap<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, CommonConsts.ENCODED_FORMAT);
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);// 指定二维码额纠错等级为中级
		hints.put(EncodeHintType.MARGIN, 2);// 图片的边距
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCodeConsts.WIDTH,
					QRCodeConsts.HEIGHT, hints);
			Path file = new File(path).toPath();
			MatrixToImageWriter.writeToPath(bitMatrix, QRCodeConsts.IMAGE_FORMAT, file);
		} catch (Exception e) {
			e.printStackTrace();
			throw new QRCodeCreateException();
		}
	}

	/**
	 * 获取保存二维码的路径
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> createPath(HttpServletRequest request) {
		Map<String, String> nameMap = new HashMap<String, String>();
		Calendar calendarStart = Calendar.getInstance();
		calendarStart.set(2018, 2, 1);
		Calendar calendarNow = Calendar.getInstance();
		int year = calendarNow.get(Calendar.YEAR) - calendarStart.get(Calendar.YEAR);
		int month = year * 12 + (calendarNow.get(Calendar.MONTH) - calendarStart.get(Calendar.MONTH));
		String splitDir = "/pic" + month + "/";
		String saveDir = request.getServletContext().getRealPath(splitDir);
		File dir = new File(saveDir);
		if (!dir.exists()) {
			dir.mkdir();
		}
		String saveName = System.currentTimeMillis() + ".png";
		String savePath = saveDir + "/" + saveName;
		String showPath = "http://www.xhban.com:8080/SC" + splitDir + saveName;
		//String showPath = "http://localhost:8080/cn.edu.xhu.SmartCar" + splitDir + saveName;
		nameMap.put("savePath", savePath);
		nameMap.put("showPath", showPath);
		return nameMap;
	}

	// 解析二维码
	public static void readQRCode(String path) {
		MultiFormatReader formatReader = new MultiFormatReader();
		File file = new File(path);
		try {
			BufferedImage image = ImageIO.read(file);
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
			Map<DecodeHintType, Object> hints = new HashMap<>();
			hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
			Result result = formatReader.decode(binaryBitmap, hints);
			System.out.println("解析结果: " + result.toString());
			System.out.println("二维码格式: " + result.getBarcodeFormat());
			System.out.println("内容：" + result.getText());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

}
