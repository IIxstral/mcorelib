package de.timc.mcorelib.title;

import java.util.ArrayList;

public class Title {
	private TitleType type;
	private ArrayList<TitleHandler> texts;
	private int ticksIn;
	private int ticksShow;
	private int ticksOut;
	private Title subtitle;

	public Title(TitleType type) {
		this.type = type;
		this.texts = new ArrayList<TitleHandler>();
		this.ticksIn = 20;
		this.ticksShow = 60;
		this.ticksOut = 20;
	}

	public void setSubTitle(Title subtitle) {
		this.subtitle = subtitle;
	}

	public Title addText(String text, TitleColor color, TitleFormat... format) {
		texts.add(new TitleHandler(text, color, format));
		return this;
	}

	public Title setTicksFadeIn(int ticks) {
		this.ticksIn = ticks;
		return this;
	}

	public Title setTicksFadeInSecond(int seconds) {
		this.ticksIn = seconds * 20;
		return this;
	}

	public Title setTicksShow(int ticks) {
		this.ticksShow = ticks;
		return this;
	}

	public Title setTicksShowSecond(int seconds) {
		this.ticksShow = seconds * 20;
		return this;
	}

	public Title setTicksFadeOut(int ticks) {
		this.ticksOut = ticks;
		return this;
	}

	public Title setTicksFadeOutSecond(int seconds) {
		this.ticksOut = seconds * 20;
		return this;
	}

	public String[] toJSON(String pN) {
		String[] ss = new String[3 + (subtitle != null ? 1 : 0)];
		ss[0] = "title " + pN + " clear";
		ss[1] = "title " + pN + " times " + ticksIn + " " + ticksShow + " " + ticksOut;
		if (subtitle != null)
			ss[2] = generate(subtitle, pN);
		ss[(subtitle != null ? 3 : 2)] = generate(this, pN);
		return ss;
	}

	private static String generate(Title t, String pN) {
		String s = "title " + pN + " " + t.type.toString().toLowerCase() + " {text:\"\",extra:[";
		for (int i = 0; i < t.texts.size(); i++) {
			TitleHandler tt = t.texts.get(i);
			s += "{text:\"" + tt.getText() + "\",";
			s += "color:" + tt.getColor() + ((tt.getFormats() != null && tt.getFormats().length > 0) ? "," : "");
			if (tt.getFormats() != null) {
				for (int j = 0; j < tt.getFormats().length; j++) {
					TitleFormat f = tt.getFormats()[j];
					s += f.toString().toLowerCase() + ":true" + (j < tt.getFormats().length - 1 ? "," : "");
				}
			}
			s += "}" + (i < t.texts.size() - 1 ? "," : "");

		}
		s += "]}";
		return s;
	}

	class TitleHandler {
		String text;
		TitleColor color;
		TitleFormat[] formats;

		private TitleHandler(String text, TitleColor color, TitleFormat[] formats) {
			this.text = text;
			this.color = color;
			this.formats = formats;
		}

		public String getText() {
			return text;
		}

		public TitleColor getColor() {
			return color;
		}

		public TitleFormat[] getFormats() {
			return formats;
		}

	}
}
