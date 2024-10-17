using Dashdoc.API.Domain.Abstract.Services;
using Dashdoc.API.Domain.Models;
using Dashdoc.API.Infrastructure.Utils;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity.UI.Services;
using Microsoft.AspNetCore.Mvc;

namespace Dashdoc.API.Server.Controllers;

[ApiController]
[Route("/api/test")]
[Authorize]
public class TestController: ControllerBase
{
    // TODO - replace with real Provider routes
    private readonly IEmailService _emailService;

    public TestController(IEmailService emailService)
    {
        _emailService = emailService;
    }

    [HttpGet]
    public ActionResult Get()
    {
        try
        {
            return Ok("ok");
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex);
            return Problem("Problem retrieving Data");
        }
    }
    
    [HttpGet("admin")]
    [Authorize(Roles = UserRoles.Admin)]
    public ActionResult GetWithPermissions([FromHeader(Name = "x-user-id")] long userId)
    {
        try
        {
            return Ok($"UserId is {userId}");
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex);
            return Problem("Problem retrieving Data");
        }
    }
    
    [HttpPost("email/{recipient}")]
    public ActionResult SendTestEmail([FromRoute] string recipient)
    {
        try
        {
            _emailService.Send(
                recipient,
                "Dashdoc Test Message",
                EmailContentBuilder.Build("TestUser")
            );
            
            return Ok("Email Sent Successfully!");
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex);
            return Problem("Problem retrieving Data");
        }
    }
}