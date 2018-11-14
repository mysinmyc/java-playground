package foo.bar;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import foo.bar.backend.ContactNotFoundException;

@Provider
public class ContactNotFoundExceptionMapper implements ExceptionMapper<ContactNotFoundException>{

	@Override
	public Response toResponse(ContactNotFoundException exception) {
		return Response.status(Status.NOT_FOUND).entity(exception.getMessage()).build();
	}

}
