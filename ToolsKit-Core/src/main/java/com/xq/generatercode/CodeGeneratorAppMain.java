
package com.xq.generatercode;


/**
 * Demo class
 *
 * @author xq
 * @date 2019/4/11
 */
public class CodeGeneratorAppMain {

	public static void main(String[] args) { 
		CodeGenerator codeGenerator = new CodeGenerator();
		codeGenerator.getAutoGenerator();
		System.out.print(System.getProperty("user.dir"));
	}

}
