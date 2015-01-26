package com.debuglife.codelabs.jcr;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.StatusLine;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.jackrabbit.webdav.DavConstants;
import org.apache.jackrabbit.webdav.DavException;
import org.apache.jackrabbit.webdav.MultiStatus;
import org.apache.jackrabbit.webdav.MultiStatusResponse;
import org.apache.jackrabbit.webdav.client.methods.CopyMethod;
import org.apache.jackrabbit.webdav.client.methods.DavMethod;
import org.apache.jackrabbit.webdav.client.methods.DeleteMethod;
import org.apache.jackrabbit.webdav.client.methods.LockMethod;
import org.apache.jackrabbit.webdav.client.methods.MkColMethod;
import org.apache.jackrabbit.webdav.client.methods.MoveMethod;
import org.apache.jackrabbit.webdav.client.methods.PropFindMethod;
import org.apache.jackrabbit.webdav.client.methods.PutMethod;
import org.apache.jackrabbit.webdav.client.methods.UnLockMethod;
import org.apache.jackrabbit.webdav.lock.Scope;
import org.apache.jackrabbit.webdav.lock.Type;


public class DavClient {

    public static void main(String [] args) throws IOException, DavException {

        HttpClient client = new HttpClient();
        Credentials creds = new UsernamePasswordCredentials("admin", "password");
        client.getState().setCredentials(AuthScope.ANY, creds);

        // MKCOL method
        DavMethod mkCol = new MkColMethod("http://127.0.0.1/uploads/test");
        client.executeMethod(mkCol);
        
        int statusCode = mkCol.getStatusCode();
        String statusText = mkCol.getStatusText();
        StatusLine statusLine = mkCol.getStatusLine();
        System.out.println("mkcol test folder:" + statusCode + " " + statusText);
        System.out.println(statusLine.toString());
        
        // Put Method
        PutMethod put = new PutMethod("http://127.0.0.1/uploads/test/lena.jpg");
        RequestEntity requestEntity = new InputStreamRequestEntity(new FileInputStream("lena.jpg"));
        put.setRequestEntity(requestEntity);
        client.executeMethod(put);
        System.out.println("put image file:" + put.getStatusCode() + " " + put.getStatusText());

        // CopyMethod(String uri, String destinationUri, boolean overwrite)
        DavMethod copy = new CopyMethod("http://127.0.0.1/uploads/test/lena.jpg",
                "http://127.0.0.1/uploads/test/lena2.jpg", true);
        client.executeMethod(copy);
        System.out.println("copy image file:" + copy.getStatusCode() + " " + copy.getStatusText());

        //MoveMethod(String uri, String destinationUri, boolean overwrite)
        DavMethod move = new MoveMethod("http://127.0.0.1/uploads/test/lena.jpg",
                "http://127.0.0.1/uploads/test/lena3.jpg", true);
        client.executeMethod(move);
        System.out.println("move image file:" + move.getStatusCode() + " " + move.getStatusText());

        //LockMethod(String uri, Scope lockScope, Type lockType, String owner, long timeout, boolean isDeep)
        LockMethod lock = new LockMethod("http://127.0.0.1/uploads/test/lena.jpg", Scope.SHARED, Type.WRITE, "admin",
                10000l, false);
        client.executeMethod(lock);
        String lockToken = lock.getLockToken();
        System.out.println("lock image file:" + lock.getStatusCode() + " " + lock.getStatusText());

        //UnLockMethod(String uri, String lockToken)
        DavMethod unlock = new UnLockMethod("http://127.0.0.1/uploads/test/lena.jpg", lockToken);
        client.executeMethod(unlock);
        System.out.println("unlock image file:" + unlock.getStatusCode() + " " + unlock.getStatusText());

        // Find Method
        DavMethod find = new PropFindMethod("http://127.0.0.1/uploads/test/", DavConstants.PROPFIND_ALL_PROP,
                DavConstants.DEPTH_1);
        client.executeMethod(find);
        MultiStatus multiStatus = find.getResponseBodyAsMultiStatus();
        MultiStatusResponse [] responses = multiStatus.getResponses();
        System.out.println("Folders and files:");
        for (int i = 0; i < responses.length; i++) {
            System.out.println(responses[i].getHref());
        }

        // Delete Method
        DavMethod delete = new DeleteMethod("http://127.0.0.1/uploads/test/");
        client.executeMethod(delete);
        System.out.println("delete test folder:" + delete.getStatusCode() + " " + delete.getStatusText());

    }
    
}
