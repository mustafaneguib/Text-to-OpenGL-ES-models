/******************
 * 
 * 
    Copyright (C) 2012  Mustafa Neguib
    Copyright (C) 2008-2012 MN Tech Solutions (www.mntechsolutions.net)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
Also add information on how to contact you by electronic and paper mail.
 * 
 * 
 */


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
