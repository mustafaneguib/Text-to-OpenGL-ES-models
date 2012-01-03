package com.ModelObjectLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

/**
 * This class is an object representation of 
 * a model containing the vertex information,
 * texture coordinates, the vertex indices
 * and drawing functionality, which is called 
 * by the renderer.
 * 
 * @originalAuthor Savas Ziplies (nea/INsanityDesign)
 * @improvedAndModified  Mustafa Neguib (www.mntechsolutions.net)
 */
public class DrawModel {

	/** The buffer holding the vertices */
	private FloatBuffer vertexBufferBuffer;
	/** The buffer holding the texture coordinates */
	private FloatBuffer textureBufferBuffer;
	private ShortBuffer indexBufferBuffer;
	private int textureImageResourceId;
	
	/** Our texture pointer */
	private int[] textureId = new int[1];

	/** 
	 * The initial vertex definition
	 * 
	 * Note that each face is defined, even
	 * if indices are available, because
	 * of the texturing we want to achieve 
	 */	
    private float vertices[];
			
    /** The initial texture coordinates (u, v) */	
    private float textures[];
    
    private short indices[];


	/**
	 * The Model constructor.
	 * 
	 * Initiate the buffers.
	 */
	public DrawModel() {
		
		
	}


	public DrawModel(float [] vertexBuffer, float [] textureBuffer, short [] facesBuffer, int textureImageResourceId) {
		
		
		
		this.vertices=vertexBuffer;
		this.textures=textureBuffer;
		this.indices=facesBuffer;
		this.textureImageResourceId=textureImageResourceId;
		
		
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(this.vertices.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		this.vertexBufferBuffer = byteBuf.asFloatBuffer();
		this.vertexBufferBuffer.put(this.vertices);
		this.vertexBufferBuffer.position(0);
	
		
		byteBuf = ByteBuffer.allocateDirect(this.textures.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		this.textureBufferBuffer = byteBuf.asFloatBuffer();
		this.textureBufferBuffer.put(this.textures);
		this.textureBufferBuffer.position(0);
		
		
		// short is 2 bytes, therefore we multiply the number if vertices with 2.
		ByteBuffer ibb = ByteBuffer.allocateDirect(this.indices.length * 3);
		ibb.order(ByteOrder.nativeOrder());
		this.indexBufferBuffer = ibb.asShortBuffer();
		this.indexBufferBuffer.put(this.indices);
		this.indexBufferBuffer.position(0);

		
	}


	/**
	 * The object own drawing function.
	 * Called from the renderer to redraw this instance
	 * with possible changes in values.
	 * 
	 * @param gl - The GL Context
	 */
	public void draw(GL10 gl) {
		//Bind our only previously generated texture in this case
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId[0]);
		
		
		//Point to our buffers
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);


		//Set the face rotation
		gl.glFrontFace(GL10.GL_CCW);
		
		//Enable the vertex and texture state
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBufferBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBufferBuffer);
		
		
		
		//gl.glDrawArrays(GL10.GL_TRIANGLES, 0, numOfVertices);
		
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,// OpenGL docs
				  GL10.GL_UNSIGNED_SHORT, indexBufferBuffer);
		
		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
	
	/**
	 * Load the textures
	 * 
	 * @param gl - The GL Context
	 * @param context - The Activity context
	 */
	public void loadGLTexture(GL10 gl, Context context) {
		//Get the texture from the Android resource directory
		InputStream is = context.getResources().openRawResource(this.textureImageResourceId);
		Bitmap bitmap = null;
		try {
			//BitmapFactory is an Android graphics utility for images
			bitmap = BitmapFactory.decodeStream(is);

		} finally {
			//Always clear and close
			try {
				is.close();
				is = null;
			} catch (IOException e) {
			}
		}

		//Generate One texture pointer...
		gl.glGenTextures(1, textureId, 0);
		//...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId[0]);
		
		//Create Nearest Filtered Texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		//Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
		
		//Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		//Clean up
		bitmap.recycle();
	}
}
