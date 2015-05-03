package de.timc.mcorelib.title;

public class TitleSamples {

	public static Title progressBar(int percent, int processBarWidth, boolean defaultSubtitle, int secTimeToShow) {
		int j = percent / processBarWidth;
		Title t = new Title(TitleType.TITLE);
		for (int k = 0; k <= processBarWidth; k++) {
			t.addText("\u2588", (k <= j ? TitleColor.green : TitleColor.gray), TitleFormat.BOLD);
		}
		t.setTicksFadeInSecond(0);
		t.setTicksFadeOutSecond(0);
		t.setTicksShowSecond(secTimeToShow);
		t.addText(" " + percent + "%", TitleColor.gold, TitleFormat.BOLD);
		if (defaultSubtitle) {
			Title sub = new Title(TitleType.SUBTITLE);
			sub.addText("Einen Moment bitte, der Ladebalken bewegt sich!", TitleColor.aqua);
			t.setSubTitle(sub);
		}
		return t;
	}
	public static String progressBarString(int percent, boolean showPercent){
		String s = "";
		int j = percent / 10;
		for (int k = 0; k <= 10; k++) {
			s += (k <= j ? "§a" : "§7") + "§l\u2588";
		}
		return s + (showPercent ? " §8" + percent + "%" : "");
	}
	public static Title progressBar(int percent, boolean defaultSubtitle, int secTimeToShow) {
		int j = percent / 10;
		Title t = new Title(TitleType.TITLE);
		for (int k = 0; k <= 10; k++) {
			t.addText("\u2588", (k <= j ? TitleColor.green : TitleColor.gray), TitleFormat.BOLD);
		}
		t.setTicksFadeInSecond(0);
		t.setTicksFadeOutSecond(0);
		t.setTicksShowSecond(secTimeToShow);
		t.addText(" " + percent + "%", TitleColor.gold, TitleFormat.BOLD);
		Title sub = new Title(TitleType.SUBTITLE);
		sub.addText("Einen Moment bitte, der Ladebalken bewegt sich!", TitleColor.aqua);
		t.setSubTitle(sub);
		return t;
	}

	public static Title progressBar(int percent, int processBarWidth, Title subtitle, int secTimeToShow) {
		int j = percent / processBarWidth;
		Title t = new Title(TitleType.TITLE);
		for (int k = 0; k <= processBarWidth; k++) {
			t.addText("\u2588", (k <= j ? TitleColor.green : TitleColor.gray), TitleFormat.BOLD);
		}
		t.setTicksFadeInSecond(0);
		t.setTicksFadeOutSecond(0);
		t.setTicksShowSecond(secTimeToShow);
		t.addText(" " + percent + "%", TitleColor.gold, TitleFormat.BOLD);
		t.setSubTitle(subtitle);
		return t;
	}
}
