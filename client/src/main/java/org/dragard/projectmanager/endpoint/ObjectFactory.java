
package org.dragard.projectmanager.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.dragard.projectmanager.endpoint package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateTask_QNAME = new QName("http://endpoint.projectmanager.dragard.org/", "createTask");
    private final static QName _CreateTaskResponse_QNAME = new QName("http://endpoint.projectmanager.dragard.org/", "createTaskResponse");
    private final static QName _DeleteTask_QNAME = new QName("http://endpoint.projectmanager.dragard.org/", "deleteTask");
    private final static QName _DeleteTaskResponse_QNAME = new QName("http://endpoint.projectmanager.dragard.org/", "deleteTaskResponse");
    private final static QName _GetTaskEndpoint_QNAME = new QName("http://endpoint.projectmanager.dragard.org/", "getTaskEndpoint");
    private final static QName _GetTaskEndpointResponse_QNAME = new QName("http://endpoint.projectmanager.dragard.org/", "getTaskEndpointResponse");
    private final static QName _GetViewTask_QNAME = new QName("http://endpoint.projectmanager.dragard.org/", "getViewTask");
    private final static QName _GetViewTaskResponse_QNAME = new QName("http://endpoint.projectmanager.dragard.org/", "getViewTaskResponse");
    private final static QName _PersistTask_QNAME = new QName("http://endpoint.projectmanager.dragard.org/", "persistTask");
    private final static QName _PersistTaskResponse_QNAME = new QName("http://endpoint.projectmanager.dragard.org/", "persistTaskResponse");
    private final static QName _SetTaskEndpoint_QNAME = new QName("http://endpoint.projectmanager.dragard.org/", "setTaskEndpoint");
    private final static QName _SetTaskEndpointResponse_QNAME = new QName("http://endpoint.projectmanager.dragard.org/", "setTaskEndpointResponse");
    private final static QName _UpdateTask_QNAME = new QName("http://endpoint.projectmanager.dragard.org/", "updateTask");
    private final static QName _UpdateTaskResponse_QNAME = new QName("http://endpoint.projectmanager.dragard.org/", "updateTaskResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.dragard.projectmanager.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateTask }
     * 
     */
    public CreateTask createCreateTask() {
        return new CreateTask();
    }

    /**
     * Create an instance of {@link CreateTaskResponse }
     * 
     */
    public CreateTaskResponse createCreateTaskResponse() {
        return new CreateTaskResponse();
    }

    /**
     * Create an instance of {@link DeleteTask }
     * 
     */
    public DeleteTask createDeleteTask() {
        return new DeleteTask();
    }

    /**
     * Create an instance of {@link DeleteTaskResponse }
     * 
     */
    public DeleteTaskResponse createDeleteTaskResponse() {
        return new DeleteTaskResponse();
    }

    /**
     * Create an instance of {@link GetTaskEndpoint }
     * 
     */
    public GetTaskEndpoint createGetTaskEndpoint() {
        return new GetTaskEndpoint();
    }

    /**
     * Create an instance of {@link GetTaskEndpointResponse }
     * 
     */
    public GetTaskEndpointResponse createGetTaskEndpointResponse() {
        return new GetTaskEndpointResponse();
    }

    /**
     * Create an instance of {@link GetViewTask }
     * 
     */
    public GetViewTask createGetViewTask() {
        return new GetViewTask();
    }

    /**
     * Create an instance of {@link GetViewTaskResponse }
     * 
     */
    public GetViewTaskResponse createGetViewTaskResponse() {
        return new GetViewTaskResponse();
    }

    /**
     * Create an instance of {@link PersistTask }
     * 
     */
    public PersistTask createPersistTask() {
        return new PersistTask();
    }

    /**
     * Create an instance of {@link PersistTaskResponse }
     * 
     */
    public PersistTaskResponse createPersistTaskResponse() {
        return new PersistTaskResponse();
    }

    /**
     * Create an instance of {@link SetTaskEndpoint }
     * 
     */
    public SetTaskEndpoint createSetTaskEndpoint() {
        return new SetTaskEndpoint();
    }

    /**
     * Create an instance of {@link SetTaskEndpointResponse }
     * 
     */
    public SetTaskEndpointResponse createSetTaskEndpointResponse() {
        return new SetTaskEndpointResponse();
    }

    /**
     * Create an instance of {@link UpdateTask }
     * 
     */
    public UpdateTask createUpdateTask() {
        return new UpdateTask();
    }

    /**
     * Create an instance of {@link UpdateTaskResponse }
     * 
     */
    public UpdateTaskResponse createUpdateTaskResponse() {
        return new UpdateTaskResponse();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link TaskEndpointServiceImpl }
     * 
     */
    public TaskEndpointServiceImpl createTaskEndpointServiceImpl() {
        return new TaskEndpointServiceImpl();
    }

    /**
     * Create an instance of {@link Bootstrap }
     * 
     */
    public Bootstrap createBootstrap() {
        return new Bootstrap();
    }

    /**
     * Create an instance of {@link Task }
     * 
     */
    public Task createTask() {
        return new Task();
    }

    /**
     * Create an instance of {@link Project }
     * 
     */
    public Project createProject() {
        return new Project();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.projectmanager.dragard.org/", name = "createTask")
    public JAXBElement<CreateTask> createCreateTask(CreateTask value) {
        return new JAXBElement<CreateTask>(_CreateTask_QNAME, CreateTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.projectmanager.dragard.org/", name = "createTaskResponse")
    public JAXBElement<CreateTaskResponse> createCreateTaskResponse(CreateTaskResponse value) {
        return new JAXBElement<CreateTaskResponse>(_CreateTaskResponse_QNAME, CreateTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.projectmanager.dragard.org/", name = "deleteTask")
    public JAXBElement<DeleteTask> createDeleteTask(DeleteTask value) {
        return new JAXBElement<DeleteTask>(_DeleteTask_QNAME, DeleteTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.projectmanager.dragard.org/", name = "deleteTaskResponse")
    public JAXBElement<DeleteTaskResponse> createDeleteTaskResponse(DeleteTaskResponse value) {
        return new JAXBElement<DeleteTaskResponse>(_DeleteTaskResponse_QNAME, DeleteTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskEndpoint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.projectmanager.dragard.org/", name = "getTaskEndpoint")
    public JAXBElement<GetTaskEndpoint> createGetTaskEndpoint(GetTaskEndpoint value) {
        return new JAXBElement<GetTaskEndpoint>(_GetTaskEndpoint_QNAME, GetTaskEndpoint.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTaskEndpointResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.projectmanager.dragard.org/", name = "getTaskEndpointResponse")
    public JAXBElement<GetTaskEndpointResponse> createGetTaskEndpointResponse(GetTaskEndpointResponse value) {
        return new JAXBElement<GetTaskEndpointResponse>(_GetTaskEndpointResponse_QNAME, GetTaskEndpointResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetViewTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.projectmanager.dragard.org/", name = "getViewTask")
    public JAXBElement<GetViewTask> createGetViewTask(GetViewTask value) {
        return new JAXBElement<GetViewTask>(_GetViewTask_QNAME, GetViewTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetViewTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.projectmanager.dragard.org/", name = "getViewTaskResponse")
    public JAXBElement<GetViewTaskResponse> createGetViewTaskResponse(GetViewTaskResponse value) {
        return new JAXBElement<GetViewTaskResponse>(_GetViewTaskResponse_QNAME, GetViewTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.projectmanager.dragard.org/", name = "persistTask")
    public JAXBElement<PersistTask> createPersistTask(PersistTask value) {
        return new JAXBElement<PersistTask>(_PersistTask_QNAME, PersistTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.projectmanager.dragard.org/", name = "persistTaskResponse")
    public JAXBElement<PersistTaskResponse> createPersistTaskResponse(PersistTaskResponse value) {
        return new JAXBElement<PersistTaskResponse>(_PersistTaskResponse_QNAME, PersistTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetTaskEndpoint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.projectmanager.dragard.org/", name = "setTaskEndpoint")
    public JAXBElement<SetTaskEndpoint> createSetTaskEndpoint(SetTaskEndpoint value) {
        return new JAXBElement<SetTaskEndpoint>(_SetTaskEndpoint_QNAME, SetTaskEndpoint.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetTaskEndpointResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.projectmanager.dragard.org/", name = "setTaskEndpointResponse")
    public JAXBElement<SetTaskEndpointResponse> createSetTaskEndpointResponse(SetTaskEndpointResponse value) {
        return new JAXBElement<SetTaskEndpointResponse>(_SetTaskEndpointResponse_QNAME, SetTaskEndpointResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.projectmanager.dragard.org/", name = "updateTask")
    public JAXBElement<UpdateTask> createUpdateTask(UpdateTask value) {
        return new JAXBElement<UpdateTask>(_UpdateTask_QNAME, UpdateTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.projectmanager.dragard.org/", name = "updateTaskResponse")
    public JAXBElement<UpdateTaskResponse> createUpdateTaskResponse(UpdateTaskResponse value) {
        return new JAXBElement<UpdateTaskResponse>(_UpdateTaskResponse_QNAME, UpdateTaskResponse.class, null, value);
    }

}
