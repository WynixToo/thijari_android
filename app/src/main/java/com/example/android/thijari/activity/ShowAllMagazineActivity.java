package com.example.android.thijari.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.android.thijari.R;
import com.example.android.thijari.adapter.MagazineListViewAdapter;
import com.example.android.thijari.dialog.PDFDownloader;
import com.example.android.thijari.dialog.TwoButtonDialog;
import com.example.android.thijari.rest.ThijariService;
import com.example.android.thijari.rest.listener.OnRetrofitResponse;
import com.example.android.thijari.rest.model.MagazineListData;
import com.example.android.thijari.util.CommonUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;


public class ShowAllMagazineActivity extends BaseActivity implements PDFDownloader.PDFDownloaderCallback {

    private String category;
    private String position;
    private Bundle b = new Bundle();
    private RecyclerView mRecyclerView;
    private MagazineListViewAdapter mAdapter;
    private ProgressBar progressBar;
    String currentFileName = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().getExtras().containsKey("CATEGORY")) {
            category = getIntent().getExtras().getString("CATEGORY");
            position = getIntent().getExtras().getString("CATEGORYID");

        }

        useToolbar(true, category);
        setupProgressBar();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager;

        layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MagazineListViewAdapter(this);
        mRecyclerView.setNestedScrollingEnabled(true);
        //mAdapter = new RecyclerViewMaterialAdapter();
        mRecyclerView.setAdapter(mAdapter);

        ThijariService.getInstance().with(this).isLoading().getMagazineList(position, new OnRetrofitResponse<List<MagazineListData>>() {
            @Override
            public void onResponse(List<MagazineListData> response) {
                isProgressBarShown(false);
                mAdapter.addAll(response);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(ResponseBody errorBody) {
                try {
                    System.out.println("RESPONCE : " + errorBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        mAdapter.setOnItemClickListener(new MagazineListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final MagazineListData magazine, int position) {

                currentFileName = magazine.getTitle().replace(" ", "_") + ".pdf";

                if (!CommonUtil.getInstance(ShowAllMagazineActivity.this).isPDFExisting(currentFileName)) {
                    TwoButtonDialog dialog = new TwoButtonDialog(ShowAllMagazineActivity.this, new TwoButtonDialog.onTowButtonDialogListener() {
                        @Override
                        public void onBtn1Click(String param) {
                            Intent myIntent = new Intent(ShowAllMagazineActivity.this, InAppWebView.class);
                            myIntent.putExtra("URL", magazine.getItem_link());
                            myIntent.putExtra("PDFNAME", magazine.getTitle() + ".pdf");
                            ShowAllMagazineActivity.this.startActivity(myIntent);
                        }

                        @Override
                        public void onBtn2Click(String param) {
//                        if (result != null) {
                            PDFDownloader pdf = new PDFDownloader(currentFileName, 0, magazine.getImageURL(),
                                    ShowAllMagazineActivity.this);
                            pdf.setListener(ShowAllMagazineActivity.this);
                            pdf.execute(magazine.getItem_link());

                        }
                    });
                    dialog.show();
                    dialog.setCustomTitle("Category : " + category);
                    dialog.setLabel1("Title : " + magazine.getTitle());

                } else {
                    openMagazinePDFView(currentFileName, magazine.getItem_link());
                }

            }
        });
    }

    private void openMagazinePDFView(String fileName, String filelink) {
        Intent myIntent = new Intent(this, MagazineContentActivity.class);
        myIntent.putExtra("PDFLINK", filelink);
        myIntent.putExtra("PDFNAME", fileName);
        this.startActivity(myIntent);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.showallmagazine_activity;
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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onFragmentCallback(Object Data, String from) {

    }

    @Override
    public void onpdfCallback(Object param) {
        if (!param.toString().equals("CANCEL DOWNLOAD")) {
            openMagazinePDFView(((Bundle) param).getString("PDFNAME"), ((Bundle) param).getString("PDFLINK"));
        }
    }
}
