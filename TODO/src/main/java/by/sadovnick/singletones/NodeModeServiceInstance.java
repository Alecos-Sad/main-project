package by.sadovnick.singletones;

import by.sadovnick.controller.NodeModelService;
import by.sadovnick.controller.impl.NodeModelServiceImpl;

public class NodeModeServiceInstance {

    private static NodeModeServiceInstance INSTANCE;
    private final NodeModelService nodeModelService;

    private NodeModeServiceInstance() {
        nodeModelService = new NodeModelServiceImpl();
    }

    public static NodeModeServiceInstance getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NodeModeServiceInstance();
        }
        return INSTANCE;
    }

    public NodeModelService getNodeModelService() {
        return nodeModelService;
    }
}
