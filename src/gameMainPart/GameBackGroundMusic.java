package gameMainPart;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

class GameBackGroundMusic implements Runnable {

	private static final int BUFFER_SIZE = 1024;
//	��Ƶ�ļ���
	private String fileName;
	
//	��ʼ������
	public GameBackGroundMusic(String fileName) {
		this.fileName = fileName;
	}
	
//	run()������ѭ����������ֱ���������
	@Override
	public void run() {
		
		while(GamePlay.state != GamePlay.STATE.EXIT)
			this.playMusic();
	}
	
//	�������ַ���
	private void playMusic() {
	//	����Ƶ�ļ�
		File file = new File(this.fileName);
		
	//	����Ƶ��
		AudioInputStream ais = null;
		try {
			ais = AudioSystem.getAudioInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
	//	����Ƶ��ʽ��������Ƶ��������������Ƶ������Ϣ�����
		AudioFormat af = null;
		SourceDataLine sdl = null;
		DataLine.Info dli = null;
		try {
			af = ais.getFormat();
		//	���Դ������
			dli = new DataLine.Info(SourceDataLine.class, af);
			sdl = (SourceDataLine) AudioSystem.getLine(dli);
			
		//	�򿪾���ָ����ʽ����
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
			//	����Ƶ����ȡָ������������������ֽڲ������ֽ�����	
				count = ais.read(buffer, 0, buffer.length);
				
				if(count >= 0) {
				//	����Ƶ����д���Ƶ��
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
