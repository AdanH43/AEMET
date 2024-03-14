package com.politecnico.aemet.Control;

import android.content.Context;
import android.content.res.Resources;

import com.politecnico.aemet.Model.Municipio;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XlsxLector {
    public static List<Municipio> leerMunicipiosXLS(Context context, int resourceId) {
        List<Municipio> municipioList = new ArrayList<>();

        Resources resources = context.getResources();
        InputStream inputStream = resources.openRawResource(resourceId);

        try {
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet("dic23");

            for (int i = 0; i < sheet.getRows(); i++) {
                Cell cellNombre = sheet.getCell(2, i);
                Cell cellCodigo = sheet.getCell(3, i);

                String nombre = cellNombre.getContents();
                String codigo = cellCodigo.getContents();

                municipioList.add(new Municipio(nombre, codigo));
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return municipioList;
    }
}
