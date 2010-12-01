package org.eve.sd.supplier.controller;

import java.util.List;

import org.eve.main.EVE;
import org.eve.model.Model;
import org.eve.sd.supplier.Supplier;
import org.eve.view.AbstractController;
import org.eve.view.Form;
import org.eve.view.TableAssist;

public class SupplierSelectionController extends AbstractController {
    
    /* (non-Javadoc)
     * @see org.eve.ui.View#userInput(java.lang.String)
     */
    @Override
    public void userInput(String input) {
        Supplier supplier_;
        List<?> suppliers;
        Form selporform;
        Form form;
        TableAssist table;
        int ident;
        Supplier supplier = (Supplier)getObject();
        Model model = getModel();
        String action = getAction();

        if (input.equals("supplier.choose")) {
            table = getTable("suppliers");
            ident = 0;
            
            for (int k = 0; k < table.getItensSize(); k++)
                if (table.getMarkValue(k)) {
                    ident = table.getInt("supplier.ident", k);
                    break;
                }
            
            if (ident == 0) {
                setMessage(EVE.error, "select.one");
                return;
            }
            
            model.load(Supplier.class, ident, supplier);
            
            if (action.equals("supplier.show.choose"))
                call("supplier.show");
            
            if (action.equals("supplier.edit.choose"))
                call("supplier.edit");
            
            return;
        }
        
        form = getForm("main");
        ident = form.getInt("supplier.ident");
    
        if (input.equals("supplier.sel")) {
            if (ident == 0) {
                selporform = getForm("selpor");
                
                suppliers = model.select("selby_suppliers", new Object[] {
                        selporform.getStringLike("supplier.name"),
                        selporform.getStringLike("supplier.aname"),
                        selporform.getStringLike("supplier.refer")});
                
                if (suppliers == null) {
                    setMessage(EVE.error, "supplier.not.found");
                    return;
                }
                
                if (suppliers.size() == 0) {
                    setMessage(EVE.error, "supplier.select.empty");
                    return;
                }
                
                table = getTable("suppliers");
                ident = 0;
                
                for(Object object : suppliers) {
                    table.insert();
                    supplier_ = (Supplier)object;
                    table.setInt("supplier.ident", ident,
                            supplier_.getId());
                    table.setString("supplier.name", ident,
                            supplier_.getName());
                    table.setString("supplier.aname", ident,
                            supplier_.getAlternateName());
                    table.setString("supplier.refer", ident,
                            supplier_.getReference());
                    ident++;
                }
                
                if (action.equals("supplier.show.sel"))
                    call("supplier.show.choose");
                
                if (action.equals("supplier.edit.sel"))
                    call("supplier.edit.choose");
                
            } else {            
                model.load(Supplier.class, ident, supplier);
                if (supplier == null) {
                    setMessage(EVE.error, "supplier.not.found");
                    return;
                }
                
                if (action.equals("supplier.show.sel"))
                    call("supplier.show");
                
                if (action.equals("supplier.edit.sel"))
                    call("supplier.edit");
            }
        }
        
        form.setInt("supplier.ident", ident);
    }

}
