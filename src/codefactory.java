import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.tools.classfile.Annotation.element_value;

import sun.reflect.generics.tree.VoidDescriptor;

public class codefactory {
	StringBuffer Allcode=new StringBuffer();
	ArrayList<StringBuffer> fcodelist=new ArrayList<>();
	ArrayList<StringBuffer> dcodelist=new ArrayList<>();
	ArrayList<functionbox> flist;
	ArrayList<datatypebox> dlist;
	
	public codefactory(ArrayList<functionbox> f, ArrayList<datatypebox> d)
	{
		flist=f;
		dlist=d;
	}
	public StringBuffer runcode()
	{
		
		String funname,datype;
		String funtype,type1;
		String code,type2;
		//datatype code
		
		for(int j=0;j<dlist.size();j++) {
			StringBuffer sb=new StringBuffer();
			dcodelist.add(sb);
			datype=dlist.get(j).getdatatype();
			type1=dlist.get(j).gettype1();
			type2=dlist.get(j).gettype2();
			dcodelist.get(j).append("data "+datype+" = "+type1+" | "+type2);
			dcodelist.get(j).append("\r\n"+"\r\n");
			Allcode.append(dcodelist.get(j));
		}
		
		//function code
		
		for(int i=0;i<flist.size();i++)
		{
			StringBuffer sb=new StringBuffer();
			fcodelist.add(sb);
			//get string from each function box
			funname=flist.get(i).getfuncname();
			funtype=flist.get(i).getfunctype();
			code=flist.get(i).getcodearea().getText();

			//judge variable or function
			if((funname.equals("FunctionName")) && (funtype.equals("Type")) && flist.get(i).namelist.size()==1)
			{
				System.out.println("1111");
				fcodelist.get(i).append(flist.get(i).namelist.get(0).getText()+" :: "+flist.get(i).typelist.get(0).getText()+"\r\n");
				fcodelist.get(i).append(code);
			}
			else {
				//produce code
				fcodelist.get(i).append(funname+" ::");
				//judge constraints
				if(flist.get(i).conlist.size()==1)
				{
					fcodelist.get(i).append(flist.get(i).conlist.get(0).getText()+"=>");
				}
				else if(flist.get(i).conlist.size()>1)
				{
					fcodelist.get(i).append("(");
					for(int k=0;k<flist.get(i).conlist.size();k++)
					{
						if(k==0)
						{
							fcodelist.get(i).append(flist.get(i).conlist.get(k).getText());
						}
						else {
							fcodelist.get(i).append(","+flist.get(i).conlist.get(k).getText());
						}
					}
					fcodelist.get(i).append(") =>");
				}
				//function type transform
				for(int j=0;j<flist.get(i).typelist.size();j++)
				{
					fcodelist.get(i).append(flist.get(i).typelist.get(j).getText()+"->");
				}
				fcodelist.get(i).append(funtype+"\r\n");
				
				//function main code
				String[] linecode=code.split("\n");
				for(int l=0;l<linecode.length;l++)
				{
					Pattern p=Pattern.compile("case");
					Matcher m=p.matcher(linecode[l]);
					Pattern p1=Pattern.compile(":");
					Matcher m1=p1.matcher(linecode[l]);
					if(m.find()&&m1.find())
					{
						fcodelist.get(i).append(funname+" "+linecode[l].substring(m1.end(), linecode[l].length())+"=");
						fcodelist.get(i).append(linecode[l+1]+"\r\n");
						l++;
					}
					else {
						fcodelist.get(i).append(funname+" "+linecode[l]+"\r\n");
					}
					
				}
			}
			
			
			Allcode.append(fcodelist.get(i));
		}
		return Allcode;
	}
}
