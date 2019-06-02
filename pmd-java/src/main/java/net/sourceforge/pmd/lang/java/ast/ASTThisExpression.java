/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.ast;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.sourceforge.pmd.lang.ast.Node;


/**
 * The "this" expression. Related to the {@link ASTSuperExpression "super"} pseudo-expression.
 *
 * <pre class="grammar">
 *
 * ThisExpression ::= "this"
 *                  | {@link ASTClassOrInterfaceType TypeName} "." "this"
 *
 * </pre>
 */
public final class ASTThisExpression extends AbstractJavaTypeNode implements ASTPrimaryExpression, LeftRecursiveNode {

    ASTThisExpression(int id) {
        super(id);
    }


    ASTThisExpression(JavaParser p, int id) {
        super(p, id);
    }

    @Override
    public void jjtClose() {
        super.jjtClose();

        if (jjtGetNumChildren() > 0) {
            // There's a qualifier
            Node child = jjtGetChild(0);
            if (child instanceof ASTAmbiguousName) {
                this.replaceChildAt(0, ((ASTAmbiguousName) child).forceTypeContext());
            }
        }
    }

    @Nullable
    public ASTClassOrInterfaceType getQualifier() {
        return jjtGetNumChildren() > 0 ? (ASTClassOrInterfaceType) jjtGetChild(0) : null;
    }


    /**
     * Accept the visitor. *
     */
    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }


    @Override
    public <T> void jjtAccept(SideEffectingVisitor<T> visitor, T data) {
        visitor.visit(this, data);
    }


}