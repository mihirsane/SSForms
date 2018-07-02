package it.starksoftware.ssform.interfaces;

import android.widget.Spinner;

import it.starksoftware.ssform.model.FormElement;

public interface FormElementCallBack {
    void callbackFormElement(FormElement object, Object tag);
}
