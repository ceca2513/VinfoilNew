package org.vinfoil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Svetlana Stojanovic on 02-May-14.
 */

public class ReadExcelPatterns {


    public void read(Patterns S) throws Exception {
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream("\\sample_data\\patterns3.xls");
        @SuppressWarnings("resource")
        HSSFWorkbook wb = new HSSFWorkbook(fis);

        ArrayList<Parallelogram> ListOfParallelograms = new ArrayList<Parallelogram>();
        ArrayList<Rectangel> ListOfRectangels = new ArrayList<Rectangel>();
        ArrayList<Triangel> ListOfTriangels = new ArrayList<Triangel>();

        ArrayList<Parallelogram> TempParallelograms = new ArrayList<Parallelogram>();
        ArrayList<Rectangel> TempRectangels = new ArrayList<Rectangel>();
        ArrayList<Triangel> TempTriangels = new ArrayList<Triangel>();
        AdditionalStatistic as = new AdditionalStatistic();

        float x1, x2, x3, x4, y1, y2, y3, y4;
        float a1, a2, b1, b2, c1, c2, ha1, ha2, hb1, hb2;

        //HSSFSheet ws = wb.getSheet("CLUSTER");
        for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {
            HSSFSheet ws = wb.getSheetAt(sheetNum);

            // Do something here


            int rowNum = ws.getLastRowNum() + 1;
            int colNum = ws.getRow(0).getLastCellNum();
            String[][] data = new String[rowNum][colNum];
            String[][] data1 = new String[rowNum][colNum];
            String[][] data2 = new String[rowNum][colNum];
            if (sheetNum == 0) {

                for (int k = 0; k < rowNum; k++) {
                    HSSFRow row = ws.getRow(k);
                    for (int l = 0; l < colNum; l++) {
                        HSSFCell cell = row.getCell(l);
                        String value = cellToString(cell);

                        data[k][l] = value;

                    }
                }


                for (int k = 1; k < rowNum; k++) {
                    Rectangel R = new Rectangel();
                    x1 = Float.parseFloat(data[k][0]);
                    y1 = Float.parseFloat(data[k][1]);
                    x2 = Float.parseFloat(data[k][2]);
                    y2 = Float.parseFloat(data[k][3]);
                    if (x1 < x2) {
                        R.setX11(x1);
                        R.setX12(x2);
                    } else {
                        R.setX11(x2);
                        R.setX12(x1);

                    }
                    if (y1 > y2) {
                        R.setY11(y1);
                        R.setY12(y2);

                    } else {
                        R.setY11(y2);
                        R.setY12(y1);
                    }
                    a1 = Math.abs(x1 - x2);
                    b1 = Math.abs(y1 - y2);
                    R.setA(a1);
                    R.setB(b1);
                    TempRectangels.add(R);
                }

                ListOfRectangels.addAll(TempRectangels);
            } else if (sheetNum == 1) {

                for (int k = 0; k < rowNum; k++) {
                    HSSFRow row = ws.getRow(k);
                    for (int l = 0; l < colNum; l++) {
                        HSSFCell cell = row.getCell(l);
                        String value = cellToString(cell);

                        data1[k][l] = value;

                    }


                }
                float min, max;
                for (int k = 1; k < rowNum; k++) {

                    Parallelogram PAR = new Parallelogram();
                    x1 = Float.parseFloat(data1[k][0]);
                    y1 = Float.parseFloat(data1[k][1]);
                    x2 = Float.parseFloat(data1[k][2]);
                    y2 = Float.parseFloat(data1[k][3]);
                    x3 = Float.parseFloat(data1[k][4]);
                    y3 = Float.parseFloat(data1[k][5]);
                    x4 = Float.parseFloat(data1[k][6]);
                    y4 = Float.parseFloat(data1[k][7]);


                    min = Math.min(x1, x2);
                    min = Math.min(min, x3);
                    min = Math.min(min, x4);
                    max = Math.max(x1, x2);
                    max = Math.max(max, x3);
                    max = Math.max(max, x4);

                    if (Math.abs(x1 - min) < 0.000001) {
                        if (Math.abs(x2 - max) < 0.000001) {
                            PAR.setX11(x1);
                            PAR.setY11(y1);
                            PAR.setX13(x2);
                            PAR.setY13(y2);
                            if (Math.abs(y3 - y1) < 0.00001) {
                                PAR.setX12(x3);
                                PAR.setY12(y3);
                                PAR.setX14(x4);
                                PAR.setY14(y4);
                            } else {
                                PAR.setX12(x4);
                                PAR.setY12(y4);
                                PAR.setX14(x3);
                                PAR.setY14(y3);
                            }


                        } else if (Math.abs(x3 - max) < 0.000001) {
                            PAR.setX11(x1);
                            PAR.setY11(y1);
                            PAR.setX13(x3);
                            PAR.setY13(y3);

                            if (Math.abs(y2 - y1) < 0.00001) {
                                PAR.setX12(x2);
                                PAR.setY12(y2);
                                PAR.setX14(x4);
                                PAR.setY14(y4);
                            } else {
                                PAR.setX12(x4);
                                PAR.setY12(y4);
                                PAR.setX14(x2);
                                PAR.setY14(y2);
                            }


                        } else if (Math.abs(x4 - max) < 0.000001) {
                            PAR.setX11(x1);
                            PAR.setY11(y1);
                            PAR.setX13(x4);
                            PAR.setY13(y4);

                            if (Math.abs(y3 - y1) < 0.00001) {
                                PAR.setX12(x3);
                                PAR.setY12(y3);
                                PAR.setX14(x2);
                                PAR.setY14(y2);
                            } else {
                                PAR.setX12(x2);
                                PAR.setY12(y2);
                                PAR.setX14(x3);
                                PAR.setY14(y3);
                            }

                        }
                    } else if (Math.abs(x2 - min) < 0.000001) {
                        if (Math.abs(x1 - max) < 0.000001) {
                            PAR.setX11(x2);
                            PAR.setY11(y2);
                            PAR.setX13(x1);
                            PAR.setY13(y1);
                            if (Math.abs(y3 - y2) < 0.0001) {
                                PAR.setX12(x3);
                                PAR.setY12(y3);
                                PAR.setX14(x4);
                                PAR.setY14(y4);
                            } else {
                                PAR.setX12(x4);
                                PAR.setY12(y4);
                                PAR.setX14(x3);
                                PAR.setY14(y3);
                            }


                        } else if (Math.abs(x3 - max) < 0.000001) {
                            PAR.setX11(x2);
                            PAR.setY11(y2);
                            PAR.setX13(x3);
                            PAR.setY13(y3);
                            if (Math.abs(y1 - y2) < 0.0001) {
                                PAR.setX12(x1);
                                PAR.setY12(y1);
                                PAR.setX14(x4);
                                PAR.setY14(y4);
                            } else {
                                PAR.setX12(x4);
                                PAR.setY12(y4);
                                PAR.setX14(x1);
                                PAR.setY14(y1);
                            }


                        } else if (Math.abs(x4 - max) < 0.000001) {
                            PAR.setX11(x2);
                            PAR.setY11(y2);
                            PAR.setX13(x4);
                            PAR.setY13(y4);
                            if (Math.abs(y3 - y2) < 0.0001) {
                                PAR.setX12(x3);
                                PAR.setY12(y3);
                                PAR.setX14(x1);
                                PAR.setY14(y1);
                            } else {
                                PAR.setX12(x1);
                                PAR.setY12(y1);
                                PAR.setX14(x3);
                                PAR.setY14(y3);
                            }


                        }

                    } else if (Math.abs(x3 - min) < 0.00001) {
                        if (Math.abs(x2 - max) < 0.000001) {
                            PAR.setX11(x3);
                            PAR.setY11(y3);
                            PAR.setX13(x2);
                            PAR.setY13(y2);
                            if (Math.abs(y1 - y3) < 0.0001) {
                                PAR.setX12(x1);
                                PAR.setY12(y1);
                                PAR.setX14(x4);
                                PAR.setY14(y4);
                            } else {
                                PAR.setX12(x4);
                                PAR.setY12(y4);
                                PAR.setX14(x1);
                                PAR.setY14(y1);
                            }


                        } else if (Math.abs(x1 - max) < 0.000001) {
                            PAR.setX11(x3);
                            PAR.setY11(y3);
                            PAR.setX13(x1);
                            PAR.setY13(y1);
                            if (Math.abs(y2 - y3) < 0.0001) {
                                PAR.setX12(x2);
                                PAR.setY12(y2);
                                PAR.setX14(x4);
                                PAR.setY14(y4);
                            } else {
                                PAR.setX12(x4);
                                PAR.setY12(y4);
                                PAR.setX14(x2);
                                PAR.setY14(y2);
                            }


                        } else if (Math.abs(x4 - max) < 0.000001) {
                            PAR.setX11(x3);
                            PAR.setY11(y3);
                            PAR.setX13(x4);
                            PAR.setY13(y4);
                            if (Math.abs(y1 - y3) < 0.0001) {
                                PAR.setX12(x1);
                                PAR.setY12(y1);
                                PAR.setX14(x2);
                                PAR.setY14(y2);
                            } else {
                                PAR.setX12(x2);
                                PAR.setY12(y2);
                                PAR.setX14(x1);
                                PAR.setY14(y1);
                            }


                        }

                    } else if (Math.abs(x4 - min) < 0.000001) {
                        if (Math.abs(x2 - max) < 0.000001) {
                            PAR.setX11(x4);
                            PAR.setY11(y4);
                            PAR.setX13(x2);
                            PAR.setY13(y2);
                            if (Math.abs(y3 - y4) < 0.0001) {
                                PAR.setX12(x3);
                                PAR.setY12(y3);
                                PAR.setX14(x1);
                                PAR.setY14(y1);
                            } else {
                                PAR.setX12(x1);
                                PAR.setY12(y1);
                                PAR.setX14(x3);
                                PAR.setY14(y3);
                            }


                        } else if (Math.abs(x3 - max) < 0.000001) {
                            PAR.setX11(x4);
                            PAR.setY11(y4);
                            PAR.setX13(x3);
                            PAR.setY13(y3);
                            if (Math.abs(y2 - y4) < 0.0001) {
                                PAR.setX12(x2);
                                PAR.setY12(y2);
                                PAR.setX14(x1);
                                PAR.setY14(y1);
                            } else {
                                PAR.setX12(x1);
                                PAR.setY12(y1);
                                PAR.setX14(x2);
                                PAR.setY14(y2);
                            }


                        } else if (Math.abs(x1 - max) < 0.000001) {
                            PAR.setX11(x4);
                            PAR.setY11(y4);
                            PAR.setX13(x1);
                            PAR.setY13(y1);
                            if (Math.abs(y3 - y4) < 0.0001) {
                                PAR.setX12(x3);
                                PAR.setY12(y3);
                                PAR.setX14(x2);
                                PAR.setY14(y2);
                            } else {
                                PAR.setX12(x2);
                                PAR.setY12(y2);
                                PAR.setX14(x3);
                                PAR.setY14(y3);
                            }


                        }

                    }

                    a1 = Math.abs(PAR.getX11() - PAR.getX12());
                    PAR.setA(a1);
                    b1 = Math.abs(PAR.getY11() - PAR.getY13());
                    PAR.setHa(b1);


                    TempParallelograms.add(PAR);
                }
                ListOfParallelograms.addAll(TempParallelograms);
            } else if (sheetNum == 2) {
                for (int k = 0; k < rowNum; k++) {
                    HSSFRow row = ws.getRow(k);
                    for (int l = 0; l < colNum; l++) {
                        HSSFCell cell = row.getCell(l);
                        String value = cellToString(cell);
                        data2[k][l] = value;

                    }
                }
                float min, max;
                for (int k = 1; k < rowNum; k++) {
                    Triangel T = new Triangel();

                    x1 = Float.parseFloat(data2[k][0]);
                    y1 = Float.parseFloat(data2[k][1]);
                    x2 = Float.parseFloat(data2[k][2]);
                    y2 = Float.parseFloat(data2[k][3]);
                    x3 = Float.parseFloat(data2[k][4]);
                    y3 = Float.parseFloat(data2[k][5]);
                    min = Math.min(x1, x2);
                    min = Math.min(min, x3);
                    max = Math.max(x1, x2);
                    max = Math.max(max, x3);
                    if (Math.abs(x1 - min) < 0.0000001) {
                        if (Math.abs(x2 - max) < 0.000001) {
                            T.setX11(x1);
                            T.setX12(x3);
                            T.setX13(x2);
                            T.setY11(y1);
                            T.setY12(y3);
                            T.setY13(y2);
                        } else if (Math.abs(x3 - max) < 0.000001) {
                            T.setX11(x1);
                            T.setX12(x2);
                            T.setX13(x3);
                            T.setY11(y1);
                            T.setY12(y2);
                            T.setY13(y3);
                        }
                    } else if (Math.abs(x2 - min) < 0.00001) {
                        if (Math.abs(x1 - max) < 0.000001) {
                            T.setX11(x2);
                            T.setX12(x3);
                            T.setX13(x1);
                            T.setY11(y2);
                            T.setY12(y3);
                            T.setY13(y1);
                        } else if (Math.abs(x3 - max) < 0.000001) {
                            T.setX11(x2);
                            T.setX12(x1);
                            T.setX13(x3);
                            T.setY11(y2);
                            T.setY12(y1);
                            T.setY13(y3);
                        }
                    } else if (Math.abs(x3 - min) < 0.000001) {
                        if (Math.abs(x2 - max) < 0.000001) {
                            T.setX11(x3);
                            T.setX12(x1);
                            T.setX13(x2);
                            T.setY11(y3);
                            T.setY12(y1);
                            T.setY13(y2);
                        } else if (Math.abs(x1 - max) < 0.000001) {
                            T.setX11(x3);
                            T.setX12(x2);
                            T.setX13(x1);
                            T.setY11(y3);
                            T.setY12(y2);
                            T.setY13(y1);
                        }
                    }
                    a1 = as.Distance(T.getX11(), T.getY11(), T.getX12(), T.getY12());
                    b1 = as.Distance(T.getX12(), T.getY12(), T.getX13(), T.getY13());
                    c1 = as.Distance(T.getX11(), T.getY11(), T.getX13(), T.getY13());
                    T.setA(a1);
                    T.setB(b1);
                    T.setC(c1);
                    TempTriangels.add(T);

                }
                ListOfTriangels.addAll(TempTriangels);
            }


        }

        S.setParalleorgrams(ListOfParallelograms);
        S.setRectangels(ListOfRectangels);
        S.setTriangels(ListOfTriangels);
        fis.close();
    }


    public static String cellToString(HSSFCell cell) {
        int type;
        Object result;
        type = cell.getCellType();
        switch (type) {
            case 0://numerical values in Excel
                result = cell.getNumericCellValue();
                break;
            case 1://String values in Excel
                result = cell.getStringCellValue();
                break;
            default:
                throw new RuntimeException("There are no support this type of cell");

        }

        return result.toString();

    }


}
   



