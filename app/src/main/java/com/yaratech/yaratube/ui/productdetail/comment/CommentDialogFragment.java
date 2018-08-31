package com.yaratech.yaratube.ui.productdetail.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.source.local.db.database.AppDataBase;

public class CommentDialogFragment extends DialogFragment
        implements View.OnClickListener, CommentContract.View {

    private EditText etComment;
    private Button btnRecord;
    private RatingBar rbRate;
    private CommentContract.Presenter iaPresenter;
    private int productId;
    public static final String KEY_PRODUCT_ID = "productId";

    public static CommentDialogFragment newInstance(int productId) {
        CommentDialogFragment fragment = new CommentDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PRODUCT_ID,productId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AppDataBase database = AppDataBase.newInstance(getActivity());
        iaPresenter = new CommentPresenter(this, getContext(),database);
        Bundle bundle = getArguments();
        if (bundle == null) return;
        setProductId(bundle.getInt(KEY_PRODUCT_ID));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.comment_dialog_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rbRate = view.findViewById(R.id.comment_dialog_fragment_rb_rate);
        etComment = view.findViewById(R.id.comment_dialog_fragment_et_comment);
        btnRecord = view.findViewById(R.id.comment_dialog_fragment_btn_record);
        btnRecord.setOnClickListener(this);
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.comment_dialog_fragment_btn_record:
                String commentText = etComment.getText().toString();
                if (!(commentText.isEmpty())) {
                    btnRecord.setClickable(false);
                    Comment comment = new Comment();
                    comment.setScore((int) rbRate.getRating());
                    comment.setCommentText(commentText);
                    comment.setTitle("");
                    iaPresenter.sendComment(productId,comment);
                }
                break;
        }
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
        btnRecord.setClickable(true);
    }

    @Override
    public void dismissDialog() {
        dismiss();
    }

    @Override
    public void showToast() {
        Toast.makeText(this.getContext(), R.string.your_comment_recorded_successfully, Toast.LENGTH_SHORT).show();
    }
}