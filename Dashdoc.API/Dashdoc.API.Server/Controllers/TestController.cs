using Dashdoc.API.Domain.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Dashdoc.API.Server.Controllers;

[ApiController]
[Route("/api/test")]
public class TestController: ControllerBase
{
    public TestController() { }

    // TODO - replace with real Provider routes
    [HttpGet]
    [Authorize(Roles = UserRoles.AgencyAdministrator)]
    public ActionResult Get()
    {
        try
        {
            return Ok("Success");
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex);
            return Problem("Problem retrieving Data");
        }
    }
}