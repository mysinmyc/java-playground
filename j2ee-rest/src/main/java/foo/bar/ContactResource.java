package foo.bar;

import foo.bar.backend.Contact;
import foo.bar.backend.ContactNotFoundException;
import foo.bar.backend.ContactRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactResource {

	@Inject
	ContactRepository contactRepository;

	@Context
	UriInfo uriInfo;

	@GET
	public List<Contact> list() {
		return contactRepository.list();
	}

	@POST
	public Response create(Contact contact) {
		contactRepository.insert(contact);

		URI createdContactUri = uriInfo.getBaseUriBuilder().path(ContactResource.class)
				.path(ContactResource.class,"findById").build(contact.getId());
		return Response.created(createdContactUri).build();
	}

	@PUT
	public void update(Contact contact) {
		contactRepository.update(contact);
	}

	@GET
	@Path("/{id}")
	public Contact findById(@PathParam("id") Long id) throws ContactNotFoundException {
		return contactRepository.findById(id);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) throws ContactNotFoundException {
		Contact contactToDelete = contactRepository.findById(id);
		if (contactToDelete == null) {
			throw new RuntimeException("not found");
		}
		contactRepository.delete(contactToDelete);
	}

}
