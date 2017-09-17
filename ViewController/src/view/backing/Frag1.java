package view.backing;

import java.io.IOException;

import java.text.ParseException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;

import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import view.backing.UploadData;

public class Frag1 {
    private RichPanelSplitter ps1;
    private RichInputFile if1;
    private RichPanelTabbed pt1;
    private RichShowDetailItem tab1;
    private RichShowDetailItem tab2;
    private RichShowDetailItem tab3;
    private RichTable t1;
    private RichTable t2;
    private RichTable t3;

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setIf1(RichInputFile if1) {
        this.if1 = if1;
    }

    public RichInputFile getIf1() {
        return if1;
    }

    public void setPt1(RichPanelTabbed pt1) {
        this.pt1 = pt1;
    }

    public RichPanelTabbed getPt1() {
        return pt1;
    }

    public void setTab1(RichShowDetailItem tab1) {
        this.tab1 = tab1;
    }

    public RichShowDetailItem getTab1() {
        return tab1;
    }

    public void setTab2(RichShowDetailItem tab2) {
        this.tab2 = tab2;
    }

    public RichShowDetailItem getTab2() {
        return tab2;
    }

    public void setTab3(RichShowDetailItem tab3) {
        this.tab3 = tab3;
    }

    public RichShowDetailItem getTab3() {
        return tab3;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }

    public void setT2(RichTable t2) {
        this.t2 = t2;
    }

    public RichTable getT2() {
        return t2;
    }

    public void setT3(RichTable t3) {
        this.t3 = t3;
    }

    public RichTable getT3() {
        return t3;
    }
    UploadData upload=new UploadData();
    public void onChangeUpload(ValueChangeEvent valueChangeEvent) {
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
         UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
         
        if (file.getContentType().equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
                        file.getContentType().equalsIgnoreCase("application/xlsx") ||
                        file.getContentType().equalsIgnoreCase("application/kset")) {
                        System.out.println("Inside XLSX loop");

            // readNProcessExcelx(file.getInputStream()); //for xlsx

            try {
                upload.readNProcessExcelx(file.getInputStream(), t1, t2, t3);
            } catch (IOException e) {
            } catch (InvalidFormatException e) {
            } catch (ParseException e) {
            }
        }else{
            System.out.println("Inside else loop");
             FacesMessage msg = new FacesMessage("File format not supported.-- Upload XLS or XLSX file");
             msg.setSeverity(FacesMessage.SEVERITY_WARN);
             FacesContext.getCurrentInstance().addMessage(null, msg);
        }
         
    }
}
