package com.example.android.thijari.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.thijari.R;
import com.example.android.thijari.util.CommonUtil;
import com.example.android.thijari.util.UtilMethod;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;

import static java.lang.String.format;

public class MagazineContentActivity extends BaseActivity implements
        OnPageChangeListener, CommonUtil.PDFDataCallback {

    private int pageNumber = 1;
    PDFView pdfView;
    String pdfName = "";
    TextView pagingnum;
    File currentFile;
    private Toolbar toolbar;
    private String pdflink;
    private ProgressDialog pdialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magazine_content_activity);
        String pdfFile = getIntent().getExtras().getString("PDFNAME");
        pdfName = pdfFile;
        pdflink = getIntent().getExtras().getString("PDFLINK");

        useToolbar(true, pdfName);

        pdialog = new ProgressDialog(this);
        pdialog.setMessage("Please Wait...");
        pdialog.setIndeterminate(false);
        pdialog.setMax(100);
        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdialog.setCancelable(false);
        if (UtilMethod.isConnection(this)) {
            pdialog.show();
            CommonUtil.getInstance(this).getRawFileSize(pdflink, this);
        } else {
            displayPDF(pdfName, true);
        }

        pagingnum = (TextView) findViewById(R.id.paging_num);
        pdfView = (PDFView) findViewById(R.id.pdfView);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.magazine_content_activity;
    }

    @Override
    protected boolean isMainActivity() {
        return false;
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    private void displayPDF(String filename, boolean jumpToFirstPage) {
        if (jumpToFirstPage)
            pageNumber = 1;
        setTitle(pdfName = filename);
        String fullPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/WebsightCacheFiles/pdf";
        currentFile = new File(fullPath, filename);
        if (currentFile != null) {
            if (currentFile.length() > 0) {
                // if (UtilMethod.isConnection(this)) {
                // if (currentFile.length() == rawFileLength) {
                // pdfView.fromFile(currentFile).defaultPage(pageNumber)
                // .onPageChange(this).load();
                // } else {
                // Toast.makeText(
                // this,
                // "Corrupted PDF file, Please delete and download again.",
                // Toast.LENGTH_SHORT).show();
                // }
                // } else {
                pdfView.fromFile(currentFile).defaultPage(pageNumber)
                        .onPageChange(this).load();
            } else {
                Toast.makeText(
                        this,
                        "Corrupted PDF file, Please delete and download again.",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void displayPDFwithChecking(String filename, int rawfilesize,
                                        boolean jumpToFirstPage) {
        if (jumpToFirstPage)
            pageNumber = 1;
        setTitle(pdfName = filename);
        String fullPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/WebsightCacheFiles/pdf";
        currentFile = new File(fullPath, filename);
        if (currentFile != null) {
            if (currentFile.length() == rawfilesize) {
                // if (UtilMethod.isConnection(this)) {
                // if (currentFile.length() == rawFileLength) {
                // pdfView.fromFile(currentFile).defaultPage(pageNumber)
                // .onPageChange(this).load();
                // } else {
                // Toast.makeText(
                // this,
                // "Corrupted PDF file, Please delete and download again.",
                // Toast.LENGTH_SHORT).show();
                // }
                // } else {
                pdfView.fromFile(currentFile).defaultPage(pageNumber)
                        .onPageChange(this).load();
            } else {
                Toast.makeText(
                        this,
                        "Corrupted PDF file, Please delete and download again.",
                        Toast.LENGTH_SHORT).show();
            }
        }

        // if (UtilMethod.isConnection(this)) {
        // if (currentFile.length() < 0
        // || currentFile.length() != ViewController.getInstance(
        // this).getRawFileSize(pdflink)) {
        // Toast.makeText(
        // this,
        // "Corrupted PDF file, Please delete and download again.",
        // Toast.LENGTH_SHORT).show();
        // } else {
        // pdfView.fromFile(currentFile).defaultPage(pageNumber)
        // .onPageChange(this).load();
        // }
        // // System.out.println(pdflink+ "    "+currentFile.length() +
        // // "   " +
        // // ViewController.getInstance(this).getRawFileSize(pdflink));
        // } else {
        // if (currentFile.length() < 0) {
        // Toast.makeText(
        // this,
        // "Corrupted PDF file, Please delete and download again.",
        // Toast.LENGTH_SHORT).show();
        // } else {
        // pdfView.fromFile(currentFile).defaultPage(pageNumber)
        // .onPageChange(this).load();
        // }
        // }

    }

    public void setFragment(Fragment frag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(android.R.id.content) == null) {
            fragmentManager.beginTransaction().add(android.R.id.content, frag)
                    .commit();
        }

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();

        int showallposition = getIntent().getExtras().getInt("POSITION");
        Bundle b = new Bundle();
        b.putInt("POSITION", showallposition);
        // ViewController.getInstance(MagazineContentActivity.this).openView(ViewName.SHOWALLMAGAZINE_VIEW,
        // b);
        this.finish();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        // TODO Auto-generated method stub
        System.out.println("page  = " + page + "     " + pageCount);
        if (page < 0 && pageCount < 0) {
            Toast.makeText(
                    this,
                    "The pdf format is invalid. Please remove and download again",
                    Toast.LENGTH_LONG).show();
        } else {
            pageNumber = page;
            pagingnum.setText(format("%s %s / %s", "Page:", page, pageCount));
        }

        // setTitle(format("%s %s / %s", pdfName, page, pageCount));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.magazine_content_menu, menu);
        // Get its ShareActionProvider
        return true;
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    // Yes button clicked
                    if (currentFile != null)
                        currentFile.delete();

                    MagazineContentActivity.this.finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    // No button clicked
                    dialog.dismiss();
                    break;
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//		if (item.getItemId() == R.id.delete_tab) {
//			AlertDialog.Builder builder = new AlertDialog.Builder(this);
//			builder.setMessage("Are you sure want to delete?")
//					.setPositiveButton("Yes", dialogClickListener)
//					.setNegativeButton("No", dialogClickListener).show();
//		} else {
//			this.finish();
//		}

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onPDFDataCallback(Object object) {
        // TODO Auto-generated method stub
        pdialog.dismiss();
        displayPDFwithChecking(pdfName, (Integer) object, true);
    }

    @Override
    public void onFragmentCallback(Object Data) {

    }

}
