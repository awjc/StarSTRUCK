package cutscenes;

import graphics.Fonts;
import graphics.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import storypanel.StoryPanel;
import utilities.ArrayUtilities;
import utilities.GeneralUtilities;
import animatables.Position;
import animatables.ZigZag;
import animation.AnimationComponent;
import animation.CutsceneAnimator;
import animation.SceneAnimator;
import animation.SingleAnimator;
import astronomy.Planet;
import astronomy.Star;
import astronomy.Sun;

public final class Cutscene1 {
	private static JPanel comp;
	private static StoryPanel story;
	private static StoryPanel story2;
	private static AnimationComponent ssComp;
	private static AnimationComponent wholeComp;
	private static Position sunPosition;
	
	private static Random rand = new Random();
	private static CutsceneAnimator anim;
	
	private static boolean setup = false;
	
	private Cutscene1(){}
	
	public static void setup(){
		comp = new JPanel();
		
		anim = new CutsceneAnimator();
		ssComp = new AnimationComponent();
		wholeComp = new AnimationComponent();
		
		SceneAnimator sanim0 = new SceneAnimator(anim, wholeComp);
		SceneAnimator sanim1 = new SceneAnimator(anim, wholeComp);
		SceneAnimator sanim2 = new SceneAnimator(anim, ssComp);
		
		final JFrame frame = GUI.getFrame();
		
		comp.setBackground(Color.BLACK);
		comp.setSize(frame.getContentPane().getSize());
		comp.setLayout(null);

		frame.getContentPane().add(comp);
		
		ssComp.setSize(comp.getHeight(), comp.getHeight());
		wholeComp.setSize(comp.getSize());
		
		sunPosition = new Position(ssComp.getWidth() / 2, ssComp.getHeight() / 2);
		
		comp.add(ssComp);
		comp.add(wholeComp);

		Image nightSkyImg = null;
		try {
			nightSkyImg = ImageIO.read(Class.class.getResource("/images/nightsky.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		sanim2.setBackground(nightSkyImg);
	
		setupStoryPanel();
		setupSAnim0(sanim0);
		setupSAnim1(sanim1);
		setupSolarSystem(sanim2);
	
		anim.addScene(sanim0);
		anim.addScene(sanim1).addStory(story, 1);
		anim.addScene(sanim2).addStory(story2, 2);
		
		setup = true;
	}
	
	public static void run(){
		if(!setup){
			setup();
		}
		
		if(anim != null){
			anim.run();
		}
	}

	private static void setupSAnim0(SceneAnimator sanim){
		int h = wholeComp.getHeight();
		int w = wholeComp.getWidth();
		int cx = w/2;
		int cy = h/2;
		int t = 2;
		
//		sanim.addAnimator(new SingleAnimator(wholeComp)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0), 2)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -w/2, 0, -70, 0, -50, 0, -30, 0, -10, 0, 1, 0, 2, 0, 3, 0,
//						4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, w/2, 0), 4)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -w/2, 0, -70, 0, -50, 0, -30, 0, -10, 0, 1, 0, 2, 0, 3, 0,
//						4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, w/2, 0), 7)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -w/2, 0, -70, 0, -50, -120, -30, 75, -10, 0, 1, 0, 2, 0, 3, 0,
//						4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, w/2, 0), 7.20)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -w/2, 0, -70, 0, -50, -135, -30, 90, -10, 0, 1, 0, 2, 0, 3, 0,
//						4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, w/2, 0), 7.35)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -w/2, 0, -70, 0, -50, 0, -30, 0, -10, 0, 1, 0, 2, 0, 3, 0,
//						4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, w/2, 0), 7.50)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -w/2, 0, -70, 0, -50, 0, -30, 0, -10, 0, 1, 0, 2, 0, 3, 0,
//						4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, w/2, 0), 9)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -w/2, 0, -250, 0, -235, 0, -220, 0, -200, 0, -180, 0, 50, 0, 
//						75, 0, 90, 0, 115, 0, 130, 0, 145, 0, 155, 0, 170, 0, w/2, 0), 10)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -w/2, 0, -250, 0, -235, -40, -220, 0, -205, 40, -180, 0, 50, 0, 
//						85, 0, 105, 0, 115, 0, 140, 0, 155, 0, 165, 0, 175, 0, w/2, 0), 10.15)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -w/2, 0, -250, 0, -235, -50, -220, 0, -205, 50, -180, 0, 50, 0, 
//						85, -190, 105, -70, 115, -125, 140, 90, 160, 0, 175, -65, 190, 0, w/2, 0), 10.3)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -w/2, 0, -250, 0, -235, -40, -220, 0, -205, 40, -180, 0, 50, 0, 
//						85, -210, 105, -80, 115, -135, 140, 100, 160, 0, 175, -75, 190, 0, w/2, 0), 10.45)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -w/2, 0, -250, 0, -235, 0, -220, 0, -205, 0, -180, 0, 50, 0, 
//						85, 0, 105, 0, 115, 0, 140, 0, 160, 0, 175, 0, 190, 0, w/2, 0), 10.65)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -w/2, 0, -250, 0, -235, 0, -220, 0, -205, 0, -180, 0, 50, 0, 
//						85, 0, 105, 0, 115, 0, 140, 0, 160, 0, 175, 0, 190, 0, w/2, 0), 15).setRemoveAfterDone(true))
//			.addAnimator(new SingleAnimator(wholeComp)
//				.addItem(new ZigZag.G(3).addPoints(t, cy - 1, w - t - 1, cy - 1, w - t - 1, cy + 1, t, cy + 1, t, cy - 1), 15)
//				.addItem(new ZigZag.G(3).addPoints(t, t, w - t - 1, t, w - t - 1, h - t - 1, t, h - t - 1, t, t), 20)
//				.addItem(new ZigZag.G(3).addPoints(t, t, w - t - 1, t, w - t - 1, h - t - 1, t, h - t - 1, t, t), 22).setRemoveAfterDone(true));
		
//		sanim.addAnimator(new SingleAnimator(wholeComp)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0), 2)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -w/2, 0, -70, 0, -50, 0, -30, 0, -10, 0, 1, 0, 2, 0, 3, 0,
//						4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, w/2, 0), 4)
//				.addItem(new ZigZag.G().addPointsCentered(cx, cy, -w/2, 0, -70, 0, -50, 0, -30, 0, -10, 0, 1, 0, 2, 0, 3, 0,
//						4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, w/2, 0), 7).setRemoveAfterDone(true))
//	
//			.addAnimator(new SingleAnimator(wholeComp)
//				.addItem(new ZigZag.G(3).addPoints(t, cy - 1, w - t - 1, cy - 1, w - t - 1, cy + 1, t, cy + 1, t, cy - 1), 6.9)
//				.addItem(new ZigZag.G(3).addPoints(t, t, w - t - 1, t, w - t - 1, h - t - 1, t, h - t - 1, t, t), 12)
//				.addItem(new ZigZag.G(3).addPoints(t, t, w - t - 1, t, w - t - 1, h - t - 1, t, h - t - 1, t, t), 14).setRemoveAfterDone(true));
	}
	
	private static void setupSAnim1(SceneAnimator sanim){
		int h = wholeComp.getHeight();
		int w = wholeComp.getWidth();
		int t = 2;
		
		int storyRunTime = 1;
		sanim.setRunTime(storyRunTime);
		
		if(Fonts.isSupported("FreeMono")){
			story = new StoryPanel(Color.GREEN, new Font("FreeMono", Font.PLAIN, 20));
		}
		else{
			story = new StoryPanel(Color.GREEN, Fonts.getMonospaced(18));
		}
		
		story.setBackground(Color.BLACK);
		story.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		
		int storyWidth = w - 2*t - 1;
		int storyHeight = h - 2*t - 1;
		story.setStoryWidth(storyWidth);
		story.setStoryHeight(storyHeight);
		story.setLocation(t, t);
		
		story.setStartDelay(0);
		
		story.addText(" ", 0, 1000);
//		story.addText("Oh, ", 3, 400)
//			.addText("someone's there", 1, 600)
//			.addText(".....", 0, 100)
//			
//			.addText("\n\nHave you come to help us?", 3, 600)
//			.addText("\nI'm glad to hear that.", 3, 600)
//			.addText("\n\nCome, there's no time to waste! ", 2, 600);
		
		
		wholeComp.add(story);
	}
	
	private static void setupSolarSystem(SceneAnimator anim){
		setupLoopStars(anim);
		setupLoopPlanets(anim);
		setupLoopSun(anim);
	}
	
	private static void setupStoryPanel(){
		if(Fonts.isSupported("FreeMono")){
			story2 = new StoryPanel(Color.GREEN, new Font("FreeMono", Font.PLAIN, 20));
		}
		else{
			story2 = new StoryPanel(Color.GREEN, Fonts.getMonospaced(18));
		}
		
		story2.addText("...", 3, 600)
			.addText("\n...", 2, 600)
			.addText("\nWhat a terrible fate we've met with...", 1, 600)
			.addText("\n\nSuch loss.. such terror..", 2, 600)
			.addText("\n\nNot anymore though, now that you've come. ", 13, 600);
	
		
		story2.setBackground(Color.BLACK);
		story2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
				
		story2.setStoryWidth(comp.getWidth() - ssComp.getWidth());
		story2.setStoryHeight(comp.getHeight());
		story2.setLocation(ssComp.getWidth(), comp.getHeight() - story2.getStoryHeight());
		
		comp.add(story2);
	}
	
	private static void setupLoopSun(SceneAnimator anim) {
		final SingleAnimator sunAnim = new SingleAnimator(ssComp, true).setRemoveAfterDone(true).setName("sun");
		
		double flashPerMin = 30;
		double time=0;
		final int beamMin = 5;
		final int beamMax = 10;
		for(int i=0; i < 3; i++){
			sunAnim.addItem(new Sun(sunPosition, 30)
					.setBeamHeight(beamMin + (i % 2 != 0 ? beamMax - beamMin : 0))
					.setAntiAlias(true).setBeamSpace(3), time);

			time += 30.0 / flashPerMin;
		}
		
//		new Timer().schedule(new TimerTask() {
//			@Override
//			public void run() {
//				sunAnim.pause();
//			}
//		}, 10000);
//		
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
//				sunAnim.resume();
				sunAnim.clear();
				
				sunAnim.addItem(new Sun(sunPosition, 30)
					.setBeamHeight(beamMin)
					.setAntiAlias(true).setBeamSpace(3), 0);
					
				sunAnim.addItem(new Sun(sunPosition, 30)
					.setBeamHeight(20)
					.setAntiAlias(true).setBeamSpace(-1)
					.setNBeams(24), 2);
				
				sunAnim.addItem(new Sun(sunPosition, 30)
					.setBeamHeight(50)
					.setAntiAlias(true).setBeamSpace(-1)
					.setNBeams(48), 4);
				
				sunAnim.addItem(new Sun(sunPosition, 30)
					.setBeamHeight(100)
					.setAntiAlias(true).setBeamSpace(-1)
					.setNBeams(150), 6);
				
				sunAnim.addItem(new Sun(sunPosition, 30)
					.setBeamHeight(500)
					.setAntiAlias(true).setBeamSpace(-1)
					.setNBeams(300), 8.5);
				
				sunAnim.addItem(new Sun(sunPosition, 30)
					.setBeamHeight(1000)
					.setAntiAlias(true).setBeamSpace(-1)
					.setNBeams(600), 10.5);
				
				sunAnim.addItem(new Sun(sunPosition, 30)
					.setBeamHeight(1500)
					.setAntiAlias(true).setBeamSpace(-1)
					.setNBeams(1000), 11.5);
				
				sunAnim.addItem(new Sun(sunPosition, 30)
					.setBeamHeight(2000)
					.setAntiAlias(true).setBeamSpace(-1)
					.setNBeams(1500), 12.5);
				
				sunAnim.addItem(new Sun(sunPosition, 30)
					.setBeamHeight(2000)
					.setAntiAlias(true).setBeamSpace(-1)
					.setNBeams(1500), 13.5);
				
				sunAnim.addItem(new Sun(Color.BLACK, sunPosition, 30)
					.setBeamHeight(2000)
					.setAntiAlias(true).setBeamSpace(-1)
					.setNBeams(1500), 17);
				
				sunAnim.setRepeat(false);
				sunAnim.setRemoveAfterDone(false);
				sunAnim.setName("monstersun");
				
				
			}
		}, 53000);
		
		anim.addAnimator(sunAnim);
	}
	
	private static void setupLoopPlanets(SceneAnimator anim) {
		SingleAnimator[] planetAnims = new SingleAnimator[9];

		//   					          M   V    E    M    J    S    U    N    P
		final int[] planetDists =      { 65, 90, 117, 143, 180, 225, 268, 303, 328};
		final int[] planetRadii =      {  6,  9,  10,   8,  18,  18,  15,  11,   6};
		final double[] planetRPMs =   {  15, 12,  10, 8.5,   7,   5,   4,   3,   2};
		int[] planetDegOffsets = new int[9];
		
		Random rand = new Random();
		for(int i=0; i < 9; i++){
			planetDegOffsets[i] = rand.nextInt(360);

			planetRPMs[i] *= (rand.nextDouble()*2 + 4.0) / 5.0;
		}
		
		Arrays.sort(planetRPMs);
		ArrayUtilities.reverse(planetRPMs);
		
		double time;
		for(int i=0; i < 9; i++){
			planetAnims[i] = new SingleAnimator(ssComp, true).setRemoveAfterDone(true)
											.setName("planet" + i);
			double degStep = 0.5;
			time = 0;
			for(double deg = planetDegOffsets[i]; deg <= (360 + planetDegOffsets[i]); deg += degStep){
				int xPos = sunPosition.getX() + (int) (planetDists[i] * Math.sin(Math.toRadians(deg)));
				int yPos = sunPosition.getY() + (int) (planetDists[i] * Math.cos(Math.toRadians(deg)));
				
				planetAnims[i].addItem(new Planet(i, new Position(xPos, yPos), planetRadii[i]), time);
				time = (double) (deg - planetDegOffsets[i] + degStep) / planetRPMs[i] * 60.0 / 360.0;
			}
		}
		
		for(int i=0; i < 9; i++){
			anim.addAnimator(planetAnims[i]);
		}
	}
	
	private static void setupLoopStars(SceneAnimator anim) {
		int nStars = 50;
		double flashPerMin;
		double time;
		
		SingleAnimator[] starAnims = new SingleAnimator[nStars];
		Color starColor1 = new Color(255, 255, 30);
		Color starColor2 = new Color(255, 255, 200);
		
		for(int i = 0; i < nStars; i++){
			flashPerMin = rand.nextInt(40) + 10;
			time = 0;
			starAnims[i] = new SingleAnimator(ssComp, true).setRemoveAfterDone(true).setName("star");
			
			Position pos;
			do {
				pos = new Position(rand.nextInt(ssComp.getHeight()), rand.nextInt(ssComp.getHeight()));
			} while(GeneralUtilities.between(pos.getX(), 250, 450) && GeneralUtilities.between(pos.getY(), 250, 450));
			
			starAnims[i].addItem(new Star(starColor1, pos, 2, 4, 4), time);
			time += 60.0 / flashPerMin;
			
			starAnims[i].addItem(new Star(starColor2, pos, 2, 4, 6), time);
			time += 60.0 / flashPerMin;
			
			starAnims[i].addItem(new Star(starColor1, pos, 2, 4, 4), time);
			
			anim.addAnimator(starAnims[i]);
		}
	}
	
	public static void $(){
//		b.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				SceneAnimator anim2 = new SceneAnimator(ssComp);
//				
//				final int planetMoveTime = 4;
//				
//				ArrayList<SingleAnimator> stars = anim.getAnimatorList("star");
//				for(int i=0; i < stars.size(); i++){
//					Star star = (Star) stars.get(i).getInterpolator().clone();
//					anim2.addAnimator(new SingleAnimator(ssComp)
//							.addItem(star, 0)
//							.addItem(star, 1));
//				}
//				
//				for(int i=0; i < 9; i++){
//					SingleAnimator planetAnim = new SingleAnimator(ssComp);
//					SingleAnimator sanim = anim.getAnimator("planet" + i);
//					
//					Animatable a = null;
//					if(sanim != null){
//						a = sanim.getInterpolator();
//						planetAnim.addItem(a, 0);
//						
//						Planet p = ((Planet) a).clone();
//						p.setCenter(new Position(sunPosition.getX() + 
//								planetDists[p.getPlanetNumber()], sunPosition.getY()));
//						
//						planetAnim.addItem(p, planetMoveTime);
//						
//						anim2.addAnimator(planetAnim);	
//					}
//				}
//				
//				Sun s = (Sun) anim.getAnimator("sun").getInterpolator().clone();
//				anim2.addAnimator(new SingleAnimator(ssComp)
//						.addItem(s, 0)
//						.addItem(s, 5)
//						.setRemoveAfterDone(true));
//				
//				anim2.addAnimator(new SingleAnimator(ssComp)
//						.addItem(s, 5)
//						.addItem(new Sun(sunPosition, 40), 10));
//				
//				anim.stop();
//				anim2.run();
//			}
//		});
	}
}
