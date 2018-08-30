package it.starksoftware.ssformsdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hsalf.smilerating.BaseRating;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.starksoftware.ssform.DateSwitcher.DateSwitcher;
import it.starksoftware.ssform.helper.FormBuildHelper;
import it.starksoftware.ssform.interfaces.ButtonCallBack;
import it.starksoftware.ssform.interfaces.CheckBoxCallBack;
import it.starksoftware.ssform.interfaces.DateSwitcherCallBack;
import it.starksoftware.ssform.interfaces.DateTimeCallBack;
import it.starksoftware.ssform.interfaces.FormElementCallBack;
import it.starksoftware.ssform.interfaces.RatingCallBack;
import it.starksoftware.ssform.interfaces.RatingSmileCallBack;
import it.starksoftware.ssform.interfaces.SearchableSpinnerCallBack;
import it.starksoftware.ssform.interfaces.SegmentCallBack;
import it.starksoftware.ssform.interfaces.SpinnerCallBack;
import it.starksoftware.ssform.interfaces.SwitchCallBack;
import it.starksoftware.ssform.model.FormDivider;
import it.starksoftware.ssform.model.FormElement;
import it.starksoftware.ssform.model.FormElementAttach;
import it.starksoftware.ssform.model.FormElementButton;
import it.starksoftware.ssform.model.FormElementCheckBox;
import it.starksoftware.ssform.model.FormElementCustomKeyboard;
import it.starksoftware.ssform.model.FormElementDateSwitcher;
import it.starksoftware.ssform.model.FormElementDateTime;
import it.starksoftware.ssform.model.FormElementImageMultipleView;
import it.starksoftware.ssform.model.FormElementImageView;
import it.starksoftware.ssform.model.FormElementInputLayout;
import it.starksoftware.ssform.model.FormElementMemo;
import it.starksoftware.ssform.model.FormElementPlaceDialog;
import it.starksoftware.ssform.model.FormElementProfileView;
import it.starksoftware.ssform.model.FormElementRating;
import it.starksoftware.ssform.model.FormElementSearchableSpinner;
import it.starksoftware.ssform.model.FormElementSegment;
import it.starksoftware.ssform.model.FormElementSignature;
import it.starksoftware.ssform.model.FormElementSmileRating;
import it.starksoftware.ssform.model.FormElementSpinner;
import it.starksoftware.ssform.model.FormElementSwitch;
import it.starksoftware.ssform.model.FormElementToken;
import it.starksoftware.ssform.model.FormHeader;
import it.starksoftware.ssform.model.FormObject;
import it.starksoftware.ssform.model.FormSpinnerObject;
import it.starksoftware.ssform.model.FormTokenObject;
import it.starksoftware.ssform.model.Validator;

public class MainActivity extends AppCompatActivity implements
        ButtonCallBack,
        DateTimeCallBack,
        RatingCallBack,
        SearchableSpinnerCallBack,
        SegmentCallBack,
        SpinnerCallBack,
        SwitchCallBack,
        CheckBoxCallBack,
        DateSwitcherCallBack,
        RatingSmileCallBack, FormElementCallBack

{

    // FORM ELEMENTS
    public FormDivider formDivider;
    public FormElement formElement;
    public FormElementAttach formElementAttach;
    public FormElementButton formElementButton;
    public FormElementDateTime formElementDateTime;
    public FormElementDateTime formElementDate;
    public FormElementDateTime formElementTime;
    public FormElementDateTime formElementDateTime1;
    public FormElementDateTime formElementDate2;
    public FormElementDateTime formElementTime3;
    public FormElementImageMultipleView formElementImageMultipleView;
    public FormElementImageView formElementImageView;
    public FormElementMemo formElementMemo;
    public FormElementRating formElementRating;
    public FormElementSearchableSpinner formElementSearchableSpinner;
    public FormElementSegment formElementSegment;
    public FormElementSignature formElementSignature;
    public FormElementSpinner formElementSpinner;
    public FormElementSwitch formElementSwitch;
    public FormElementCheckBox formElementCheckBox;
    public FormElementPlaceDialog formElementPlaceDialog;
    public FormElement formElementPickerSingle;
    public FormElement formElementPickerMultiple;
    public FormElementToken formElementToken;
    public FormElementDateSwitcher formElementDateSwitcher;
    public FormElementInputLayout formElementInputLayout;
    public FormHeader formHeader;
    public FormElementProfileView formElementProfileView;
    public FormElementSmileRating formElementSmileRating;


    // BUILDER
    public FormBuildHelper mFormBuilder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public ArrayList<RadioButton> segmentedButtons;
    public ArrayList<FormSpinnerObject> objSpinner;
    public ArrayList<FormTokenObject> objTokens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFormBuilder = new FormBuildHelper(this, this, recyclerView, getSupportFragmentManager());
        setupForm();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_item:
                removeItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void removeItem()
    {
        mFormBuilder.hideFormElement(mFormBuilder.getFormElement(10));
        mFormBuilder.refreshView();
    }

    public void setupForm() {

        Calendar calendar = Calendar.getInstance();

        formElement = FormElement.createInstance()
                .setTitle("BASIC ELEMENT")
                .setType(FormElement.TYPE_EDITTEXT_TEXT_SINGLELINE)
                .setRequired(true)
                .setRequiredResponseMessage("!!! REQUIRED !!!")
                .setTag(10);

        formElementPickerMultiple = FormElement.createInstance()
                .setTitle("MULTIPLE PICKER")
                .setType(FormElement.TYPE_PICKER_MULTI_CHECKBOX)
                .setOptions(multiPickerValues());

        formElementAttach = FormElementAttach.createInstance()
                .setTitle("ATTACH")
                .setContext(this)
                .setTag(20);

        formElementButton = FormElementButton.createInstance()
                .setTitle("BUTTON")
                .setButtonCallBack(this)
                .setTag(30);

        formElementDateTime = FormElementDateTime.createInstance()
                .setTitle("DATE TIME")
                .setType(FormElementDateTime.TYPE_PICKER_DATE_TIME)
                .setDetafultDate(new Date())
                .setMinDate(new Date())
                .setValue(new Date())
                .setCallback(this)
                .setTag(50);

        formElementTime = FormElementDateTime.createInstance()
                .setTitle("TIME")
                .setType(FormElementDateTime.TYPE_PICKER_TIME)
                .setDetafultDate(new Date())
                .setMinDate(new Date())
                .setValue(new Date())
                .setCallback(this)
                .setTag(51);

        formElementDate = FormElementDateTime.createInstance()
                .setTitle("DATE")
                .setType(FormElementDateTime.TYPE_PICKER_DATE)
                .setDetafultDate(new Date())
                .setMinDate(new Date())
                .setValue(new Date())
                .setCallback(this)
                .setTag(53);


        formElementImageMultipleView = FormElementImageMultipleView.createInstance()
                .setTitle("MULTI IMAGES")
                .setActivity(this)
                .setContext(this)
                .setMaxImages(10)
                .setTag(60);

        formElementImageView = FormElementImageView.createInstance()
                .setTitle("IMAGE")
                .setTag(70);

        formElementMemo = FormElementMemo.createInstance()
                .setTitle("MEMO")
                .setTag(80);

        formElementRating = FormElementRating.createInstance()
                .setTitle("RATING")
                .setCallBack(this)
                .setTag(90);

        formElementSearchableSpinner = FormElementSearchableSpinner.createInstance()
                .setCallback(this)
                .setActivity(this)
                .setContext(this)
                .setDialogTitle("Dialog Title")
                .setSpinnerObject(spinnerValues())
                .setTitle("SEARCH VIEW")
                .setTag(100);

        formElementPlaceDialog = FormElementPlaceDialog.createInstance()
                .setActivity(this)
                .setContext(this)
                .setDialogTitle("Enter location name here")
                .setTitle("PLACE DIALOG")
                .setTag(105);

        formElementSegment = FormElementSegment.createInstance()
                .setTitle("SEGMENT")
                .setCallBack(this)
                .setSegmentedButtons(segmentValues())
                .setValueCheckA(true)
                .setTag(110);

        formElementSignature = FormElementSignature.createInstance()
                .setTitle("SIGNATURE")
                .setActivity(this)
                .setContext(this)
                .setTag(120);

        formElementSpinner = FormElementSpinner.createInstance()
                .setTitle("SPINNER")
                .setSpinnerObject(spinnerValues())
                .setActivity(this)
                .setContext(this)
                .setCallback(this)
                .setTag(130);

        formElementCheckBox = FormElementCheckBox.createInstance()
                .setTitle("CheckBOX")
                .setActivity(this)
                .setContext(this)
                .setCallback(this)
                .setValue(true)
                .setTag(140);

        formElementSwitch = FormElementSwitch.createInstance()
                .setTitle("SWITCH")
                .setCallBack(this)
                .setTag(150);

        formElementToken = FormElementToken.createInstance()
                .setTitle("TOKENS")
                .setTokensObject(tokensValues())
                .setTag(250);

        formElementDateSwitcher = FormElementDateSwitcher.createInstance()
                .setTitle("DATE SWITCHER")
                .setDateSwitcherType(DateSwitcher.Type.FORNIGHT)
                .setDateSwitcherCallBack(this)
                .setTag(251);

        formElementInputLayout = FormElementInputLayout.createInstance()
                .setmHint("BASIC ELEMENT LAYOUT")
                .setType(FormElement.TYPE_EDITTEXT_TEXT_SINGLELINE)
                .setRequired(true)
                .setRequiredResponseMessage("!!! REQUIRED !!!")
                .setTag(252);

        Bitmap imgProfile = BitmapFactory.decodeResource(getResources(), R.drawable.iron_man);
        formElementProfileView = FormElementProfileView.createInstance()
                .setProfileName("Tony Stark")
                .setProfileImage(imgProfile)
                .setTag(999);

        HashMap<Integer, String> smileTitleByValue = new HashMap<Integer, String>();
        smileTitleByValue.put(BaseRating.GOOD, "GOOD");
        smileTitleByValue.put(BaseRating.TERRIBLE, "BLEAA");

        formElementSmileRating = FormElementSmileRating.createInstance()
                .setTitle("SMILE RATING")
                .setValue(0)
                .setRatingSmileCallBack(this)
                .setSmileTitleByValue(smileTitleByValue)
                .setTag(1098);


        formElementDateTime1 = FormElementDateTime.createInstance()
                .setTitle("DATE TIME")
                .setType(FormElementDateTime.TYPE_PICKER_DATE_TIME)
                .setDetafultDate(new Date())
                .setMinDate(new Date())
                .setValue(new Date())
                .setCallback(this)
                .setTag(54);

        formElementTime3 = FormElementDateTime.createInstance()
                .setTitle("TIME")
                .setType(FormElementDateTime.TYPE_PICKER_TIME)
                .setDetafultDate(new Date())
                .setMinDate(new Date())
                .setValue(new Date())
                .setCallback(this)
                .setTag(55);

        formElementDate2 = FormElementDateTime.createInstance()
                .setTitle("DATE")
                .setType(FormElementDateTime.TYPE_PICKER_DATE)
                .setDetafultDate(new Date())
                .setMinDate(new Date())
                .setValue(new Date())
                .setCallback(this)
                .setTag(56);

        //formHeader = FormHeader.createInstance().setTitle("BUTTON").setTag(130);

        List<FormObject> formItems = new ArrayList<>();
        //formItems.add(formDivider);
        formItems.add(formElementProfileView);
        formItems.add(formElementSmileRating);
        formItems.add(formElement);
        formItems.add(formElementInputLayout);
        formItems.add(formElementToken);
        formItems.add(formElementDateSwitcher);
        formItems.add(formElementPickerMultiple);
        formItems.add(formElementAttach);
        formItems.add(formElementButton);
        formItems.add(formElementDateTime);
        formItems.add(formElementDate);
        formItems.add(formElementTime);
        formItems.add(formElementImageMultipleView);
        formItems.add(formElementImageView);
        formItems.add(formElementMemo);
        formItems.add(formElementRating);
        formItems.add(formElementSearchableSpinner);
        formItems.add(formElementSegment);
        formItems.add(formElementSignature);
        formItems.add(formElementSpinner);
        formItems.add(formElementSwitch);
        formItems.add(formElementCheckBox);
        formItems.add(formElementPlaceDialog);

        formItems.add(formElementDateTime1);
        formItems.add(formElementDate2);
        formItems.add(formElementTime3);

        mFormBuilder.addFormElements(formItems);
        mFormBuilder.refreshView();

    }

    public List<String> multiPickerValues() {
        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");
        fruits.add("Guava");

        return fruits;
    }

    public ArrayList<FormSpinnerObject> spinnerValues() {
        objSpinner = new ArrayList<FormSpinnerObject>();
        FormSpinnerObject item = new FormSpinnerObject();
        item.setKey("");
        item.setValue("-");

        for (int p = 1; p < 11; p++) {
            item = new FormSpinnerObject();
            item.setKey(String.valueOf(p));
            item.setValue(String.format(Locale.getDefault(), "Value %d", p));
            objSpinner.add(item);
        }

        return objSpinner;
    }


    public ArrayList<FormTokenObject> tokensValues() {
        objTokens = new ArrayList<FormTokenObject>();
        FormTokenObject item = new FormTokenObject();
        item.setKey("");
        item.setValue("-");

        for (int p = 1; p < 11; p++) {
            item = new FormTokenObject();
            item.setKey(String.valueOf(p));
            item.setValue(String.format(Locale.getDefault(), "Value %d", p));
            objTokens.add(item);
        }

        return objTokens;
    }

    public ArrayList<RadioButton> segmentValues() {
        segmentedButtons = new ArrayList<RadioButton>();
        RadioButton segmentedButton = new RadioButton(this);
        segmentedButton.setText("VALUE A");
        segmentedButton.setTag("A");
        segmentedButtons.add(segmentedButton);

        segmentedButton = new RadioButton(this);
        segmentedButton.setText("VALUE B");
        segmentedButton.setTag("B");
        segmentedButtons.add(segmentedButton);

        return segmentedButtons;
    }

    public void showToastMessage(String sToastMessage) {
        Toast.makeText(this, sToastMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void callbackButtonReturn(FormElementButton object, Object tag) {
        String sMessageToast = String.format("CONTROL : Button - VALUE CLICKED");
        List<Validator> validatorResult = this.mFormBuilder.validateForm();

        String sMessages = "";
        if (validatorResult.size() > 0) {
            for (int i = 0; i < validatorResult.size(); i++) {
                sMessages = sMessages + validatorResult.get(i).getValidatorMessage() + "\n";
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            TextView textView = new TextView(this);
            textView.setText(sMessages);
            builder.setCustomTitle(textView);

            builder.show();
        }

        showToastMessage(sMessageToast);
    }

    @Override
    public void callbackDateTimeReturn(Date value, FormElementDateTime object, Object tag) {
        String sMessageToast = String.format("CONTROL : DateTime - VALUE %s", value);
        showToastMessage(sMessageToast);
    }

    @Override
    public void callbackRatingReturn(float value) {
        String sMessageToast = String.format("CONTROL : Rating - VALUE %s", value);
        showToastMessage(sMessageToast);
    }

    @Override
    public void callbackRatingSmileReturn(int value) {
        String sMessageToast = String.format("CONTROL : Rating Smile - VALUE %s", value);
        showToastMessage(sMessageToast);
    }


    @Override
    public void callbackSearchableSpinnerReturn(FormElementSearchableSpinner object, Object tag, FormSpinnerObject spinnerObject) {
        String sMessageToast = String.format("CONTROL : SearchableSpinner - VALUE %s", spinnerObject.getValue());
        showToastMessage(sMessageToast);
    }

    @Override
    public void callbackSegmentReturn(RadioGroup object, Object tag) {

        ArrayList<RadioButton> item = segmentedButtons;

        String sMessageToast = String.format("CONTROL : Segment - VALUE %s", (int) tag);
        showToastMessage(sMessageToast);
    }

    @Override
    public void callbackDateSwitcherReturn(Date topDate, Date bottomDate, FormElementDateSwitcher object, Object tag) {

        String sMessageToast = String.format("CONTROL : DateSwitcher - topDate %s - bottomDate %s", topDate, bottomDate);
        showToastMessage(sMessageToast);
    }

    @Override
    public void callbackSpinnerReturn(FormSpinnerObject object, Object tag, Spinner spinner) {
        String sMessageToast = String.format("CONTROL : Spinner - VALUE %s", object.getValue());
        showToastMessage(sMessageToast);
    }

    @Override
    public void callbackSwitchReturn(FormElementSwitch object, Object tag, boolean switchStatus) {
        String sMessageToast = String.format("CONTROL : Switch - VALUE %s", switchStatus);
        showToastMessage(sMessageToast);
    }

    @Override
    public void callbackCheckBoxReturn(Object tag, CheckBox checkBox, boolean isChecked) {

        String sMessageToast = String.format("CONTROL : CheckBox - VALUE %s", isChecked);
        showToastMessage(sMessageToast);
    }

    @Override
    public void callbackFormElement(FormElement object, Object tag) {

    }
}
