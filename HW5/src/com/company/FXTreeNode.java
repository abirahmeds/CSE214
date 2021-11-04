package com.company;
/**
 *This is the FXTreeNode class which represents the nodes that are used in
 * the FXComponentTree
 *
 * @author Abir Ahmed
 * ID 112751779
 * HW #5
 * CSE 214 R03
 * TA's Name: Kevin Cheng
 */


public class FXTreeNode {
    //Member Variables
    private String text;
    private ComponentType type;
    private FXTreeNode parent;
    private FXTreeNode[] children;
    final int maxChildren = 10;

    /**
     * This is the default Constructor used to create a new FXTreeNode object
     */
    public FXTreeNode() {
        this.text = null;
        this.type = null;
        this.parent = null;
        children = new FXTreeNode[maxChildren];
    }

    /**
     * This is a Constructor used to create a new FXTreeNode object
     *
     * @param type
     *      the type of this node
     */
    public FXTreeNode(ComponentType type) {
        this.text = null;
        this.type = type;
        this.parent = null;
        children = new FXTreeNode[maxChildren];
    }

    /**
     * This is a Constructor used to create a new FXTreeNode object and to
     * initialize non-container components that contain text
     *
     * @param text
     *      the text of this node
     *
     * @param type
     *      the type of this node
     */
    public FXTreeNode(String text, ComponentType type) {
        this.text = text;
        this.type = type;
        this.parent = null;
        children = new FXTreeNode[0];
    }

    /**
     * This is a setter method
     *
     * @param text
     *      the text to set
     *
     * @return setText
     *      set the text of the FXTreeNode
     *
     * @throws NotAllowedException
     *      if the FXTreeNode is a container, it cannot have text
     */
    public void setText(String text) throws NotAllowedException {
        if (isContainer()){
            throw new NotAllowedException("Cannot add text to these " +
                    "elements.");
        }
        if (text == null) {
            return;
        }
        this.text = text;
    }


    /**
     * This is a setter method
     *
     * @param type
     *      the type to set this node to
     *
     * @return setText
     *      set the type of the FXTreeNode
     */
    public void setType(ComponentType type) { this.type = type; }

    /**
     * This is a setter method
     *
     * @param parent
     *      the parent to set for this node
     *
     * @return setParen
     *      sets the parent of the FXTreeNode
     */
    public void setParent(FXTreeNode parent){ this.parent = parent; }

    /**
     * This is a setter method
     *
     * @param children
     *      the children to set for this node
     *
     * @return setChildren
     *      sets the children of the FXTreeNode
     */
    public void setChildren(FXTreeNode[] children) throws NotAllowedException {
        if (!isContainer()){
            throw new NotAllowedException("Cannot add children to these " +
                    "elements.");
        }
        this.children = children;
    }

    /**
     * This is a setter method
     *
     * @param index
     *      the index of where to place the child
     *
     * @param child
     *      the node being placed in the children array for this node
     *
     * @return setChildrenNode
     *      sets the children of the FXTreeNode by placing child at the index
     *      provided in the input
     *
     * @throws NotAllowedException
     *      if the FXTreeNode is not a container, it cannot have children
     *
     * @throws NotAllowedException
     *      if the index is greater than 9, it is outside the length of the
     *      children array for the node
     */
    public void setChildrenNode(int index, FXTreeNode child)
            throws MaximumChildrenException, NotAllowedException{
        if (!this.isContainer()){
            throw new NotAllowedException("Cannot add children to these " +
                    "elements.");
        }
        if (index > 9) {
            throw new MaximumChildrenException("Index greater than allowed " +
                    "number of children.");
        }
        children[index] = child;
    }

    /**
     * This is a getter method
     *
     * @return getText
     *      gets the text of the FXTreeNode
     */
    public String getText() throws NotAllowedException {
        if (isContainer()){
            throw new NotAllowedException("Cannot add text to these " +
                    "elements.");
        }
        return text;
    }

    /**
     * This is a getter method
     *
     * @return getType
     *      gets the type of the FXTreeNode
     */
    public ComponentType getType() {
        return this.type;
    }

    /**
     * This is a getter method
     *
     * @return getParent
     *      gets the parent of the FXTreeNode
     */
    public FXTreeNode getParent() {
        return this.parent;
    }

    /**
     * This is a getter method
     *
     * @return getChildren
     *      gets the children of the FXTreeNode
     */
    public FXTreeNode[] getChildren() throws NotAllowedException {
        if (!isContainer()){
            throw new NotAllowedException("This item does not have any " +
                    "children.");
        }
        return children;
    }

    /**
     * This is a getter method
     *
     * @return getChildrenNode
     *      gets the child of the FXTreeNode at index
     */
    public FXTreeNode getChildrenNode(int index) throws NotAllowedException {
        if (!isContainer()){
            throw new NotAllowedException("This item does not have any " +
                    "children.");
        }
        return children[index];
    }

    /**
     * This is a boolean method
     *
     * @return boolean
     *      true if the node is a container, false if not
     */
    public boolean isContainer(){
        return this.getType() == ComponentType.AnchorPane ||
                this.getType() == ComponentType.HBox ||
                this.getType() == ComponentType.VBox;
    }

    /**
     * This is the toString method for the FXTreeNode object
     *
     * @return toString
     *      returns the printable representation of a node
     */
    public String toString() {
        String node = "";
        if (isContainer()){
            node += type.toString();
        }else{
            node += type.toString() + " " + text;
        }
        return node;
    }
}
