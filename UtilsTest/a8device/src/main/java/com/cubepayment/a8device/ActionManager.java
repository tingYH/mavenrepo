package com.cubepayment.a8device;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;

import com.usdk.apiservice.aidl.printer.AlignMode;
import com.usdk.apiservice.aidl.printer.OnPrintListener;
import com.usdk.apiservice.aidl.printer.UPrinter;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ChengKai YH
 * @version $
 * <p/>
 * <p/> $
 */

public class ActionManager implements ActionInterface, DeviceHelper.ServiceReadyListener {
    private Context ctx;
    private boolean useEpayModule;
    @Override
    public boolean actionRegister(Context ctx, boolean useEpayModule) {
        this.ctx = ctx;
        this.useEpayModule = useEpayModule;
        try {
            DeviceHelper.me().init(ctx);
            DeviceHelper.me().bindService();
            DeviceHelper.me().setServiceListener(this);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void actionPrint(String uri) {
       UPrinter printer = DeviceHelper.me().getPrinter();
       try{
//           byte[] image = readAssetsFile(ctx,"demo.png");
           Bitmap image = getBitmapFromUri(Uri.parse(uri));

           printer.addImage(AlignMode.CENTER, bitmap2Bytes(image));
           //printer.addBmpImage(0, FactorMode.BMP1X1, image);
           printer.feedLine(5);

           printer.startPrint(new OnPrintListener.Stub() {
               @Override
               public void onFinish() throws RemoteException {
                   System.out.println("onFinish");
               }

               @Override
               public void onError(int error) throws RemoteException {
                   System.out.println("onError");
               }
           });
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    @Override
    public void actionUnregister() {
        try {
            DeviceHelper.me().unregister();
            DeviceHelper.me().setServiceListener(null);
        } catch (IllegalStateException e) {
        }
    }

    public byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                ctx.getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    private static byte[] readAssetsFile(Context ctx, String fileName) {
        InputStream input = null;
        try {
            input = ctx.getAssets().open(fileName);
            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onReady(String version) {
        initRegister();
    }

    private void initRegister() {
        register(useEpayModule);
    }
    private void unregister() {
        try {
            DeviceHelper.me().unregister();
        } catch (IllegalStateException e) {
        }
    }

    private void register(boolean useEpayModule) {
        try {
            DeviceHelper.me().register(useEpayModule);
        } catch (IllegalStateException e) {
        }
    }

}
