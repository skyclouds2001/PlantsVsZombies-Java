package gameMainPart;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

class GameBackGroundMusic implements Runnable {

	private static final int BUFFER_SIZE = 1024;
//	音频文件名
	private String fileName;
	
//	初始化方法
	public GameBackGroundMusic(String fileName) {
		this.fileName = fileName;
	}
	
//	run()方法，循环调用音乐直至程序结束
	@Override
	public void run() {
		
		while(GamePlay.state != GamePlay.STATE.EXIT)
			this.playMusic();
	}
	
//	播放音乐方法
	private void playMusic() {
	//	打开音频文件
		File file = new File(this.fileName);
		
	//	打开音频流
		AudioInputStream ais = null;
		try {
			ais = AudioSystem.getAudioInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
	//	打开音频格式流对象，音频数据线流对象，音频附加信息类对象
		AudioFormat af = null;
		SourceDataLine sdl = null;
		DataLine.Info dli = null;
		try {
			af = ais.getFormat();
		//	获得源数据行
			dli = new DataLine.Info(SourceDataLine.class, af);
			sdl = (SourceDataLine) AudioSystem.getLine(dli);
			
		//	打开具有指定格式的行
			sdl.open(af);
	    } catch(Exception e) {
	    	e.printStackTrace();
			return;
	    }
		
		sdl.start();
		int count = 0;
		byte [] buffer = new byte[BUFFER_SIZE];
		try {
			while(count != -1) {
			//	从音频流读取指定的最大数量的数据字节并放入字节数组	
				count = ais.read(buffer, 0, buffer.length);
				
				if(count >= 0) {
				//	将音频数据写入混频器
					sdl.write(buffer, 0, count);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("|| Exception in music. ||");
		} finally {
			sdl.drain();
			sdl.close();
		}
		
	}

}
