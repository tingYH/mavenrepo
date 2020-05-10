package com.cubepayment.a8device;

import android.content.Context;

/**
 * @author ChengKai YH
 * @version $
 * <p/>
 * <p/> $
 */
public interface ActionInterface {

    boolean actionRegister(Context ctx,boolean useEpayModule);

    void actionPrint(String Uri);

    void actionUnregister();
}
