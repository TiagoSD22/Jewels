package Jewels;

import java.applet.AudioClip;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JApplet;

public class Sons {
	HashMap<String,AudioClip> mapa;
	AudioClip foco,queda,ponto,no,whoosh,lvl1;
	
	public Sons(){
		foco = null;
		queda = null;
		ponto = null;
		lvl1 = null;
		no = null;
		whoosh = null;
		try{
			foco = JApplet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Sons/select.wav"));
			queda = JApplet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Sons/fall.wav"));
			ponto = JApplet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Sons/match.wav"));
			lvl1 = JApplet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Sons/Temas/lvl1.wav"));
			no = JApplet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Sons/no.wav"));
			whoosh = JApplet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/Fonte/Sons/whoosh.wav"));
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
		mapa = new HashMap<String,AudioClip>(6);
		mapa.put("foco", foco);
		mapa.put("ponto", ponto);
		mapa.put("queda", queda);
		mapa.put("lvl1", lvl1);
		mapa.put("no",no);
		mapa.put("whoosh",whoosh);
	}
	
	public void Tocar(String nome){
		if(nome.equals("foco") || nome.equals("ponto") || nome.equals("queda") || nome.equals("no") || nome.equals("whoosh")){
			mapa.get(nome).play();
		}
		else{
			mapa.get(nome).loop();
		}
	}
	
	public void Parar(String nome){
		mapa.get(nome).stop();
	}
}
