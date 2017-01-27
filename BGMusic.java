import java.io.BufferedInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * 
 * @author termitavee
 * Clase para la administración de musica en segundo plano
 */
public class BGMusic {
	String source;
	Clip bgMusic;
	AudioInputStream audioStream;

	public BGMusic(String nombreArchivo) {
		try {
			bgMusic = AudioSystem.getClip();
			//source = nombreArchivo;
			//audioStream = AudioSystem.getAudioInputStream(new File(getClass().getResource(nombreArchivo).toURI()));
			
			BufferedInputStream myStream = new BufferedInputStream(getClass().getResourceAsStream(nombreArchivo)); 
			audioStream = AudioSystem.getAudioInputStream(myStream);
			
		} catch (Exception ex) {
			System.out.println("No se ha cargado " + ex.getMessage());
		}
	}
/**
 * Comienza la musica
 * @param loop valor boolean que especifica si debe repetirse al llegar al final
 */
	public void start(boolean loop) {
		try {
			// load the audio into the clip
			bgMusic.open(audioStream);
			// Start playing audio
			if (loop)
				bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
			else
				bgMusic.loop(0);
		} catch (Exception ex) {
			System.out.println("No se ha iniciado " + ex.getMessage());
		}
	}
	
	
	/**
	 *  continua la musica por el inicio
	 * @param loop valor boolean que especifica si debe repetirse al llegar al final
	 */
	public void restart(boolean loop){
		bgMusic.setFramePosition(0);
		continuar(loop);
	}
	/**
	 *  continua la musica por donde estaba en el puntero
	 * @param loop valor boolean que especifica si debe repetirse al llegar al final
	 */
	public void continuar(boolean loop){
		if (loop)
			bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
		else
			bgMusic.loop(bgMusic.getFrameLength());
	}
	/**
	 * Parar la musica
	 */
	public void stop() {
		System.out.println("BGMusic.stop()");
		try{
		//audioStream.close();
		}catch(Exception e){}
		bgMusic.stop();
	}

}
