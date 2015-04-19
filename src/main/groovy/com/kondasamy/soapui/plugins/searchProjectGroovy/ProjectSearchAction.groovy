package com.kondasamy.soapui.plugins.searchProjectGroovy

/**
 * Created by Kondasamy J
 * Contact: Kondasamy@outlook.com
 */

import com.eviware.soapui.impl.wsdl.WsdlProject
import com.eviware.soapui.model.ModelItem
import com.eviware.soapui.support.components.ModelItemListDesktopPanel
import com.eviware.soapui.support.UISupport
import com.eviware.soapui.support.action.support.AbstractSoapUIAction

class ProjectSearchAction extends AbstractSoapUIAction <WsdlProject>
{
    public ProjectSearchAction()
    {
        super("Search within Project","Helps in listing out SoapUI model items matching the search term")
    }
    @Override
    void perform(WsdlProject project, Object o)
    {
        String token = UISupport.prompt("Provide search string", "Item Search", "")
        List <ModelItem> searchResult = new ArrayList<>()
        searchItems(searchResult,project,token)

        if( searchResult.isEmpty())
        {
            UISupport.showErrorMessage( "No items matching [" + token + "] found in project");
            return;
        }
        UISupport.showDesktopPanel(new ModelItemListDesktopPanel("Search Result",
                "The following items matched [" + token + "]",
                searchResult.toArray(new ModelItem[searchResult.size()])));
    }

    private void searchItems(List<ModelItem> searchResult,ModelItem parent,String token)
    {
        for(ModelItem child : parent.getChildren())
        {
            if(child.getName().matches(token))
            {
                searchResult.add(child)
            }
            searchItems(searchResult,child,token)
        }
    }
}
/**
 * Created by Kondasamy J
 * Contact: Kondasamy@outlook.com
 */