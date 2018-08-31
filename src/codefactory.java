import java.util.ArrayList;

import sun.reflect.generics.tree.VoidDescriptor;

public class codefactory {
	StringBuffer Allcode=new StringBuffer();
	ArrayList<StringBuffer> codelist=new ArrayList<>();
	ArrayList<functionbox> flist;
	
	public codefactory(ArrayList<functionbox> f)
	{
		flist=f;
	}
	public StringBuffer runcode()
	{
		
		String funname;
		String funtype;
		String code;
		for(int i=0;i<flist.size();i++)
		{
			StringBuffer sb=new StringBuffer();
			codelist.add(sb);
			funname=flist.get(i).getfuncname();
			funtype=flist.get(i).getfunctype();
			code=flist.get(i).getcodearea().getText();
			codelist.get(i).append(funname+" ::");
			for(int j=0;j<flist.get(i).typelist.size();j++)
			{
				codelist.get(i).append(flist.get(i).typelist.get(j).getText()+"->");
			}
			codelist.get(i).append(funtype+"\r\n");
			codelist.get(i).append(code+"\r\n"+"\r\n");
			Allcode.append(codelist.get(i));
		}
		return Allcode;
	}
}
