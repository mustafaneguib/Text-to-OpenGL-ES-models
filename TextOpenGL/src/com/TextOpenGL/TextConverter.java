package com.TextOpenGL;

import com.ModelObjectLoader.GenerateTextModel;


public class TextConverter {
	
	private char character;
	private GenerateTextModel characterModel;
	
	
	public TextConverter(char character,
			GenerateTextModel characterModel) {
		super();
		this.character = character;
		this.characterModel = characterModel;
	}



	public char getCharacter() {
		return character;
	}



	public void setCharacter(char character) {
		this.character = character;
	}



	public GenerateTextModel getCharacterModel() {
		return characterModel;
	}



	public void setCharacterModel(GenerateTextModel characterModel) {
		this.characterModel = characterModel;
	}

	

}
