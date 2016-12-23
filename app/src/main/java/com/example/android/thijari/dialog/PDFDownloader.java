package com.example.android.thijari.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.android.thijari.util.PrefStorage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class PDFDownloader extends AsyncTask<String, String, String> {

	private ProgressDialog pDialog;
	private String filename;
	private Context context;
	private PDFDownloaderCallback callback;
	private int filePosition;
	private boolean isCancel = false;
	private String thumnail;

	public interface PDFDownloaderCallback {
		public void onpdfCallback(Object param);
	}

	public PDFDownloader(String fileName, int filePosition,
						 String Thumbnail_image, Context context) {

		this.filePosition = filePosition;
		this.filename = fileName;
		this.context = context;
		this.thumnail = Thumbnail_image;
		createProgressDialog(this.context);
	}

	public void setListener(PDFDownloaderCallback callback) {
		this.callback = callback;
	}

	public void createProgressDialog(Context context) {
		pDialog = new ProgressDialog(context);
		pDialog.setMessage("Downloading file. Please wait...");
		pDialog.setIndeterminate(false);
		pDialog.setMax(100);
		pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pDialog.setCancelable(false);
		pDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						PDFDownloader.this.cancel(true);
						dialog.cancel();
						isCancel = true;
						Toast.makeText(PDFDownloader.this.context,
								"Download has been cancel.", Toast.LENGTH_LONG)
								.show();
					}
				});
	}

	/**
	 * Before starting background thread Show Progress Bar Dialog
	 * */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog.show();
	}

	/**
	 * Downloading file in background thread
	 * */
	int lenghtOfFile = 0;

	String file_url;
	
	@Override
	protected String doInBackground(String... f_url) {
		int count;
		try {
			file_url = f_url[0];
			URL url = new URL(f_url[0]);
			URLConnection conection = url.openConnection();
			conection.connect();
			// this will be useful so that you can show a tipical 0-100%
			// progress bar
			lenghtOfFile = conection.getContentLength();
			InputStream input = new BufferedInputStream(url.openStream(), 8192);
			Log.d("PDF FILE", lenghtOfFile + "");
			// download the file
			String fullPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/WebsightCacheFiles/pdf";
			File dir = new File(fullPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(fullPath, this.filename);
			OutputStream output = new FileOutputStream(file);
			// OutputStream output = new FileOutputStream("/sdcard/"
			// + this.filename);

			byte data[] = new byte[1024];

			long total = 0;

			while ((count = input.read(data)) != -1) {
				if (!PDFDownloader.this.isCancelled() && !isCancel) {
					total += count;
					// publishing the progress....
					// After this onProgressUpdate will be called
					// writing data to file
					if (lenghtOfFile > 0)
						publishProgress(""
								+ (int) ((total * 100) / lenghtOfFile));
					output.write(data, 0, count);
				} else {
					input.close();
					file.delete();
					break;
				}
			}

			// flushing output
			output.flush();

			// closing streams
			output.close();
			input.close();
		} catch (Exception e) {
			Log.e("Error: ", e.getMessage());
		}

		return null;
	}

	@Override
	protected void onCancelled(String result) {
		// TODO Auto-generated method stub
		super.onCancelled(result);
	}

	/**
	 * Updating progress bar
	 * */
	protected void onProgressUpdate(String... progress) {
		// setting progress percentage
		pDialog.setProgress(Integer.parseInt(progress[0]));
	}

	/**
	 * After completing background task Dismiss the progress dialog
	 * **/

	@Override
	protected void onPostExecute(String result) {
		// dismiss the dialog after the file was downloaded

		pDialog.dismiss();

		if (isCancel) {
			callback.onpdfCallback("CANCEL DOWNLOAD");
		} else {
			if (result != null) {
				Toast.makeText(this.context, "Download error: " + result,
						Toast.LENGTH_LONG).show();

			} else {
				PrefStorage imagestore = new PrefStorage(this.context, filename);
				imagestore.savePreference(filename, "", this.thumnail);
				Toast.makeText(this.context,
						"File downloaded  " + filename + "  " + thumnail,
						Toast.LENGTH_SHORT).show();
			}

			if (callback != null) {
				 Bundle b = new Bundle();
				 b.putString("PDFNAME", filename);
				 b.putString("PDFLINK", file_url);
				callback.onpdfCallback(b);
			}
		}

		// Displaying downloaded image into image view
		// Reading image path from sdcard
		// String imagePath =
		// Environment.getExternalStorageDirectory().toString() +
		// "/downloadedfile.jpg";
		// setting downloaded into image view
		// my_image.setImageDrawable(Drawable.createFromPath(imagePath));
		// showPdf();
	}
}