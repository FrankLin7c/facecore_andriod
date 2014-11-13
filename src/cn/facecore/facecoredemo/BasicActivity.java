package cn.facecore.facecoredemo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.widget.Toast;

/**
 * @author Administrator
 * 
 */
public class BasicActivity extends Activity {

	private File dir;
	protected String photo_path;
	protected File photoFile;
	
	/**
	 *�������е�activity���� AlertDialog
	 */
	public void show() {
		// TODO Auto-generated method stub
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				BasicActivity.this);
		builder.setTitle("ѡ����Ƭ");
		builder.setPositiveButton("���", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface

			dialog, int which) {
				takeFromCamera();
			}
		});
		builder.setNegativeButton("���", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface

			dialog, int which) {
				// ��ת�����
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 1);

			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	/**
	 * ��������Ƭ
	 */
	private void takeFromCamera() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			dir = new File(Environment.getExternalStorageDirectory()
					+ "/ChinaFace/Camera");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			photo_path = dir + "/"
					+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
					+ ".jpg";
			photoFile = new File(photo_path);
			Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
			startActivityForResult(intent, 0);
		} else {
			Toast.makeText(this, "SD��������,�޷��������", Toast.LENGTH_SHORT).show();
		}
	}

}
