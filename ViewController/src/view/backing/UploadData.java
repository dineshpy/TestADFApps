package view.backing;

//import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


//import java.io.OutputStream;

import java.math.BigDecimal;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.faces.event.ActionEvent;
//import javax.faces.event.ValueChangeEvent;

//
//import oracle.adf.model.BindingContext;
//import oracle.adf.model.OperationBinding;
import oracle.adf.model.binding.DCIteratorBinding;
//import oracle.adf.view.rich.component.rich.data.RichTable;
//import oracle.adf.view.rich.component.rich.input.RichInputFile;


//import oracle.adf.view.rich.component.rich.layout.RichToolbar;
//import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
//import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
//import oracle.adf.view.rich.context.AdfFacesContext;


//import oracle.binding.BindingContainer;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.context.AdfFacesContext;

//import oracle.adfinternal.view.faces.bi.util.JsfUtils;

import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;
//import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import view.backing.Utils.ADFUtils;
import view.backing.Utils.JSFUtils;

public class UploadData {



    public void uploadExcelData(){
        
        
        
        
        
    }












public void readNProcessExcelx(InputStream xlsx, RichTable t1, RichTable t2, RichTable t3) throws IOException,
                                                                InvalidFormatException,
                                                                ParseException {

    //Use XSSFWorkbook for XLS file
    //        XSSFWorkbook WorkBook = null;
    org.apache.poi.ss.usermodel.Workbook workbook = null;
    int sheetIndex = 0;

    if (sheetIndex == 0) {
        
        CollectionModel cModel = (CollectionModel)t1.getValue();
        JUCtrlHierBinding tableBinding = (JUCtrlHierBinding)cModel.getWrappedData();        
        DCIteratorBinding iter = tableBinding.getDCIteratorBinding();
        
        try {  
            workbook = WorkbookFactory.create(xlsx);
        } catch (Exception e) {
            System.err.println("Exception in Line Workbook : " + e);
        }
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(sheetIndex);
        Integer skipRw = 1;
        Integer skipcnt = 1;
        int columnCount = 0;
        //Iterate over excel rows
        for (org.apache.poi.ss.usermodel.Row tempRow : sheet) {

           
           

            if (skipcnt == 1) {
                columnCount = tempRow.getPhysicalNumberOfCells();
            }
            if (skipcnt > skipRw) { //skip first n row for labels.
                
                //Create new row in table
                ADFUtils.findOperation("CreateInsert").execute();
                System.err.println("Line Row Added");
                oracle.jbo.Row linerow =iter.getNavigatableRowIterator().getCurrentRow();
               
                int Index = 0;
                //Iterate over row's columns
                for (int column = 0; column < columnCount; column++) {

                    Cell MytempCell = tempRow.getCell(column);
                    if (MytempCell != null) {
                        Index = MytempCell.getColumnIndex();
                    } else {
                        //Index++;
                    }
                    try {
                        if (Index == 0) {
                            if (MytempCell != null) {
                                System.err.println("Index 0 " +MytempCell.getNumericCellValue());
                                linerow.setAttribute("LineNumber",MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 1) {
                            if (MytempCell != null) {
                                System.err.println("Index 1 " +MytempCell.getStringCellValue());
                                linerow.setAttribute("ProjectNum",MytempCell.getStringCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        } else if (Index == 2) {
                            if (MytempCell != null) {
                                System.err.println("Index 2 " +MytempCell.getStringCellValue());
                                linerow.setAttribute("TaskNum",MytempCell.getStringCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Task Number is blank!!!!!!!!!");
                            }
                        } else if (Index == 3) {
                            if (MytempCell != null) {
                                System.err.println("Index 3 " +MytempCell.getStringCellValue());
                                linerow.setAttribute("LineType",MytempCell.getStringCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Line Type is blank!!!!!!!!!");
                            }
                        } else if (Index == 4) {
                            if (MytempCell != null) {
                                System.out.println("Index 4 " +MytempCell.getStringCellValue());
                                linerow.setAttribute("ItemsNum", MytempCell.getStringCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Item Number is blank!!!!!!!!!");
                            }
                        } else if (Index == 5) {
                            if (MytempCell != null) {
                                System.err.println("Index 5 " +MytempCell.getNumericCellValue());
                                linerow.setAttribute("RevNo",MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Item Revision is blank!!!!!!!!!");
                            }
                        } else if (Index == 6) {
                            if (MytempCell != null) {
                                System.err.println("Index 6 " +MytempCell.getStringCellValue());
                                linerow.setAttribute("Description",MytempCell.getStringCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Item Description is blank!!!!!!!!!");
                            }
                        } else if (Index == 7) {
                            if (MytempCell != null) {
                                System.err.println("Index 7 " +MytempCell.getStringCellValue());
                                linerow.setAttribute("Category",MytempCell.getStringCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Category Name is blank!!!!!!!!!");
                            }
                        } else if (Index == 8) {
                            if (MytempCell != null) {
                                System.err.println("Index 8 " +MytempCell.getStringCellValue());
                                linerow.setAttribute("Uom",MytempCell.getStringCellValue());
                            } else {
                                System.err.println("!!!!!!!!!UOM Code is blank!!!!!!!!!");
                            }
                        } else if (Index == 9) {
                            if (MytempCell != null) {
                                System.err.println("Index 9 " +MytempCell.getNumericCellValue());
                                linerow.setAttribute("Quantity",MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Quantity is blank!!!!!!!!!");
                            }
                        } else if (Index == 10) {
                            if (MytempCell != null) {
                                System.err.println("Index 10 " +MytempCell.getNumericCellValue());
                                linerow.setAttribute("UnitPrice",MytempCell.getNumericCellValue()); 
                            } else {
                                System.err.println("!!!!!!!!!Unit Price is blank!!!!!!!!!");
                            }
                        } else if (Index == 11) {
                            if (MytempCell != null) {
                                System.err.println("Index 11 " +MytempCell.getStringCellValue());
                                linerow.setAttribute("ProvisionType",MytempCell.getStringCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Unit Price is blank!!!!!!!!!");
                            }
                        } else if (Index == 12) {
                            if (MytempCell != null) {
                                System.err.println("Index 12 " +MytempCell.getNumericCellValue());
                                linerow.setAttribute("ProvisionAmount",MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Unit Price is blank!!!!!!!!!");
                            }
                        } else if (Index == 13) {
                            if (MytempCell != null) {
                                System.err.println("Index 13 " +MytempCell.getNumericCellValue());
                                linerow.setAttribute("RefNo",MytempCell.getNumericCellValue()); 
                            } else {
                                System.err.println("!!!!!!!!!Unit Price is blank!!!!!!!!!");
                            }
                        }

                    } catch (Exception e) {
                        System.err.println("Exception Occured at Line & column position is.... " +
                                           Index);
                        e.printStackTrace();
                    }
                }
            }
           
            skipcnt++;
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(t1);    
        JSFUtils.addFacesInformationMessage("Line Added Successfully");
    }

    /******************************************FOR SHIPMENT**********************************************/


    sheetIndex = 1;

    if (sheetIndex == 1) {

        CollectionModel cModel = (CollectionModel)t2.getValue();
        JUCtrlHierBinding tableBinding = (JUCtrlHierBinding)cModel.getWrappedData();        
        DCIteratorBinding iter = tableBinding.getDCIteratorBinding();
        
        try {
            //            WorkBook = new XSSFWorkbook(xlsx);
            workbook = WorkbookFactory.create(xlsx);
        } catch (Exception e) {
            System.err.println("Exception in Line Workbook : " + e);
        }
        org.apache.poi.ss.usermodel.Sheet sheet =workbook.getSheetAt(sheetIndex);

        Integer skipRw = 1;
        Integer skipcnt = 1;
        int columnCount = 0;
        //Iterate over excel rows
        for (org.apache.poi.ss.usermodel.Row tempRow : sheet) {

            if (skipcnt == 1) {
                columnCount = tempRow.getPhysicalNumberOfCells();
            }
            if (skipcnt > skipRw) { //skip first n row for labels.
                //Create new row in table

                ADFUtils.findOperation("CreateInsert1").execute();
                System.err.println("Ship Row Added");
                oracle.jbo.Row shiprow =iter.getNavigatableRowIterator().getCurrentRow();
                int Index = 0;
                //Iterate over row's columns
                for (int column = 0; column < columnCount; column++) {

                    Cell MytempCell = tempRow.getCell(column);
                    if (MytempCell != null) {
                        Index = MytempCell.getColumnIndex();
                    } else {
                        //Index++;
                    }
                    try {

                        if (Index == 0) {
                            if (MytempCell != null) {
                                System.err.println("shiprow Index 0 " +MytempCell.getNumericCellValue());
                                shiprow.setAttribute("LineNumber",MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Ship To Organization Name is blank!!!!!!!!!");
                            }
                        }else if (Index == 1) {
                            if (MytempCell != null) {
                                System.err.println("shiprow Index 1 " +MytempCell.getNumericCellValue());
                                shiprow.setAttribute("ShipNumber",MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 2) {
                            if (MytempCell != null) {
                                System.err.println("shiprow Index 2 " +MytempCell.getStringCellValue());
                                shiprow.setAttribute("ShipOrg",MytempCell.getStringCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 3) {
                            if (MytempCell != null) {
                                System.err.println("shiprow Index 3 " +MytempCell.getNumericCellValue());
                                shiprow.setAttribute("Quantity",MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 4) {
                            if (MytempCell != null) {
                                System.out.println(" shiprow Index 4 " +MytempCell.getNumericCellValue());
                                shiprow.setAttribute("Uom", MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 5) {
                            if (MytempCell != null) {
                                java.util.Date date = MytempCell.getDateCellValue();
                                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                String date1 = dateFormat.format(date);

                                try {
                                    date = dateFormat.parse(date1);
                                    } catch (ParseException e) {
                                    }
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                    oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
                                System.err.println(" shiprow Index 5" +jboDate);
                                    shiprow.setAttribute("NeeByDate", jboDate);
                                                                   
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 6) {
                            if (MytempCell != null) {
                                System.err.println(" shiprow Index 6 " +MytempCell.getStringCellValue());
                                shiprow.setAttribute("NoteToSupplier",MytempCell.getStringCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 7) {
                            if (MytempCell != null) {
                                java.util.Date date = MytempCell.getDateCellValue();
                                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                String date1 = dateFormat.format(date);
                                try {
                                date = dateFormat.parse(date1);
                                } catch (ParseException e) {
                                }
                                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
                                System.err.println(" shiprow Index 7" +jboDate);
                                shiprow.setAttribute("PromisedDate", jboDate);
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 8) {
                            if (MytempCell != null) {
                                System.err.println("shiprow Index 8 " +MytempCell.getNumericCellValue());
                                shiprow.setAttribute("RefNo",MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }
                        
                        }catch (Exception e) {
                        System.err.println("Exception Occured at Shipment & column position is.... " +
                                           Index);
                        e.printStackTrace();
                    }
                }
            }
            
            skipcnt++;
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(t2);    
        
//        ADFUtils.findOperation("Commit").execute();
//        AdfFacesContext.getCurrentInstance().addPartialTarget(t1);    
//        AdfFacesContext.getCurrentInstance().addPartialTarget(t2);    
//        AdfFacesContext.getCurrentInstance().addPartialTarget(t3);    
        
        JSFUtils.addFacesInformationMessage("Ship Added Successfully");
    }
    
//===================================================================================================================== 

     sheetIndex = 2;

    if (sheetIndex == 2) {

        CollectionModel cModel = (CollectionModel)t3.getValue();
        JUCtrlHierBinding tableBinding = (JUCtrlHierBinding)cModel.getWrappedData();        
        DCIteratorBinding iter = tableBinding.getDCIteratorBinding();
        
        try {
            //            WorkBook = new XSSFWorkbook(xlsx);
            workbook = WorkbookFactory.create(xlsx);
        } catch (Exception e) {
            System.err.println("Exception in Line Workbook : " + e);
        }
        org.apache.poi.ss.usermodel.Sheet sheet =workbook.getSheetAt(sheetIndex);

        Integer skipRw = 1;
        Integer skipcnt = 1;
        int columnCount = 0;
        //Iterate over excel rows
        for (org.apache.poi.ss.usermodel.Row tempRow : sheet) {

            if (skipcnt == 1) {
                columnCount = tempRow.getPhysicalNumberOfCells();
            }
            if (skipcnt > skipRw) { //skip first n row for labels.
                //Create new row in table

                ADFUtils.findOperation("CreateInsert2").execute();
                System.err.println("Dist Row Added");
                oracle.jbo.Row Distrow =iter.getNavigatableRowIterator().getCurrentRow();
                int Index = 0;
                //Iterate over row's columns
                for (int column = 0; column < columnCount; column++) {

                    Cell MytempCell = tempRow.getCell(column);
                    if (MytempCell != null) {
                        Index = MytempCell.getColumnIndex();
                    } else {
                        //Index++;
                    }
                    try {

                        if (Index == 0) {
                            if (MytempCell != null) {
                                System.err.println("Distrow Index 0 " +MytempCell.getNumericCellValue());
                                Distrow.setAttribute("LineNumber",MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Ship To Organization Name is blank!!!!!!!!!");
                            }
                        }else if (Index == 1) {
                            if (MytempCell != null) {
                                System.err.println("Distrow Index 1 " +MytempCell.getNumericCellValue());
                                Distrow.setAttribute("ShipNumber",MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 2) {
                            if (MytempCell != null) {
                                System.err.println("Distrow Index 2 " +MytempCell.getNumericCellValue());
                                Distrow.setAttribute("DistNumber",MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 3) {
                            if (MytempCell != null) {
                                System.err.println("Distrow Index 3 " +MytempCell.getNumericCellValue());
                                Distrow.setAttribute("ProjectNum",MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 4) {
                            if (MytempCell != null) {
                                System.out.println(" Distrow Index 4 " +MytempCell.getNumericCellValue());
                                Distrow.setAttribute("TaskNum", MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 5) {
                            if (MytempCell != null) {
                                System.out.println(" Distrow Index 5 " +MytempCell.getStringCellValue());
//                                Double d = MytempCell.getNumericCellValue();
//                                String comcode=d.toString();
//                                System.out.println(" Distrow Index 5 " +comcode);
                                Distrow.setAttribute("CodeComb", MytempCell.getStringCellValue());
                                                                   
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 6) {
                            if (MytempCell != null) {
                                System.err.println(" Distrow Index 6 " +MytempCell.getNumericCellValue());
                                Distrow.setAttribute("DistQty",MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 7) {
                            if (MytempCell != null) {
                               System.err.println(" Distrow Index 7 " +MytempCell.getStringCellValue());
                                Distrow.setAttribute("ExpenditureType",MytempCell.getStringCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 8) {
                            if (MytempCell != null) {
                                System.err.println("Distrow Index 8 " +MytempCell.getStringCellValue());
                                Distrow.setAttribute("ExpenditureOrganization",MytempCell.getStringCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 9) {
                            if (MytempCell != null) {
                               java.util.Date date = MytempCell.getDateCellValue();
                                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                String date1 = dateFormat.format(date);

                                try {
                                    date = dateFormat.parse(date1);
                                    } catch (ParseException e) {
                                    }
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                    oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
                                System.err.println(" Distrow Index 9" +jboDate);
                                    Distrow.setAttribute("ExpenditureDate", jboDate);
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }else if (Index == 10) {
                            if (MytempCell != null) {
                                System.err.println("Distrow Index 10 " +MytempCell.getNumericCellValue());
                                Distrow.setAttribute("RefNo",MytempCell.getNumericCellValue());
                            } else {
                                System.err.println("!!!!!!!!!Project Number is blank!!!!!!!!!");
                            }
                        }
						
                        
                        }catch (Exception e) {
                        System.err.println("Exception Occured at Shipment & column position is.... " +
                                           Index);
                        e.printStackTrace();
                    }
                }
            }
            
            skipcnt++;
        }


//    AdfFacesContext.getCurrentInstance().addPartialTarget(t3);    
        ADFUtils.findOperation("Commit").execute();
         AdfFacesContext.getCurrentInstance().addPartialTarget(t1);
         AdfFacesContext.getCurrentInstance().addPartialTarget(t2);
         AdfFacesContext.getCurrentInstance().addPartialTarget(t3);
         
    
    JSFUtils.addFacesInformationMessage("Ship Added Successfully");
    }

            








}
}