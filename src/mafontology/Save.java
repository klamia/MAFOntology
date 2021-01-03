/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mafontology;

import java.io.File;
//import java.util.Date;
import java.io.IOException;
import java.util.Vector;
import jxl.*;
import jxl.write.*;

/**
 *
 * @author Administrateur
 */
public  class Save {
    public static  String Chemin;
    
    public static void main(String[] args) throws IOException, WriteException  {
          Vector v1 = new Vector();/*Cont1 1er entite du couple*/
          Vector v2 = new Vector();
          int index=0;

          v2.add(index, "HotelRoom");
          v1.add(index, "Room");
          index++;
          v2.add(index, "OneRoom");
          v1.add(index, "SingleRoom");
          index++;
          v2.add(index, "Suite");
          v1.add(index, "Suite");
          index++;
          v2.add(index, "TownHouse");
          v1.add(index, "TownHouse");
          index++;
          v2.add(index, "NumBeds");
          v1.add(index, "NumBedAttribute");
          index++;
          v2.add(index, "SmokingPreference");
          v1.add(index, "SmokingPreference");
          index++;
          v2.add(index, "OnFloor");
          v1.add(index, "FloorAttribute");


        WritableWorkbook workbook = Workbook.createWorkbook(new File(Chemin));
        WritableSheet sheet = workbook.createSheet("First Sheet",0);
      for (int i =0; i< v1.size();i++)
        {
            Label label = new Label (i,2,v1.get(i).toString());
            sheet.addCell((WritableCell) label);
        }
   //    WritableCellFormat integerFormat = new WritableCellFormat (NumberFormats.INTEGER);
        Label number = new Label(0, 4,"AA");
        sheet.addCell((WritableCell) number);

        workbook.write();
        workbook.close();

    }
}
